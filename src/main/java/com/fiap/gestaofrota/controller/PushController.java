package com.fiap.gestaofrota.controller;

import com.fiap.gestaofrota.entity.PushTokenEntity;
import com.fiap.gestaofrota.repository.PushTokenRepository;
import com.fiap.gestaofrota.service.PushService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/push")
public class PushController {
    private final PushTokenRepository repo;
    private final PushService pushService;

    public PushController(PushTokenRepository repo, PushService pushService) {
        this.repo = repo;
        this.pushService = pushService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Map<String, Object> body) {
        String token = body.get("token") == null ? null : body.get("token").toString();
        String platform = body.get("platform") == null ? null : body.get("platform").toString();
        String meta = body.get("appVersion") == null ? null : body.get("appVersion").toString();
        if (token == null || token.isBlank()) return ResponseEntity.badRequest().body(Map.of("error", "token required"));
        PushTokenEntity e = repo.findByToken(token).orElseGet(PushTokenEntity::new);
        e.setToken(token);
        e.setPlatform(platform);
        e.setMeta(meta);
        repo.save(e);
        try {
            pushService.sendSimpleNotificationToToken(token, "App", "Notificações habilitadas");
        } catch (Exception ignored) {
        }
        return ResponseEntity.ok(Map.of("ok", true));
    }

}
