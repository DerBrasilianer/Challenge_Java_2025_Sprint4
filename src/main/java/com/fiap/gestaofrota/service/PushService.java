package com.fiap.gestaofrota.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiap.gestaofrota.entity.PushTokenEntity;
import com.fiap.gestaofrota.repository.PushTokenRepository;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PushService {
    private final RestTemplate rest;
    private final ObjectMapper mapper;
    private final PushTokenRepository pushRepo;
    private final String EXPO_URL = "https://exp.host/--/api/v2/push/send";

    public PushService(PushTokenRepository pushRepo) {
        this.rest = new RestTemplate();
        this.mapper = new ObjectMapper();
        this.pushRepo = pushRepo;
    }

    public void sendSimpleNotificationToToken(String token, String title, String body) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            Map<String, Object> payload = Map.of(
                    "to", token,
                    "title", title,
                    "body", body
            );
            HttpEntity<String> entity = new HttpEntity<>(mapper.writeValueAsString(payload), headers);
            ResponseEntity<String> resp = rest.postForEntity(EXPO_URL, entity, String.class);
            handleResponse(resp);
        } catch (Exception ignored) {
        }
    }

    public void sendSimpleNotificationToAll(String title, String body) {
        List<String> tokens = pushRepo.findAll().stream().map(PushTokenEntity::getToken).collect(Collectors.toList());
        for (String t : tokens) {
            sendSimpleNotificationToToken(t, title, body);
        }
    }

    private void handleResponse(ResponseEntity<String> resp) {
        try {
            if (resp == null || resp.getStatusCode() != HttpStatus.OK) return;
            JsonNode root = mapper.readTree(resp.getBody());
            if (root.isArray()) {
                for (JsonNode item : root) {
                    if (item.has("status") && "error".equals(item.get("status").asText())) {
                        if (item.has("details") && item.get("details").has("error") && item.has("to")) {
                            String error = item.get("details").get("error").asText();
                            String to = item.get("to").asText();
                            if ("DeviceNotRegistered".equals(error) || "InvalidCredentials".equals(error) || "MessageTooBig".equals(error)) {
                                pushRepo.findByToken(to).ifPresent(pushRepo::delete);
                            }
                        }
                    }
                }
            } else if (root.has("data")) {
            }
        } catch (Exception ignored) {
        }
    }

}
