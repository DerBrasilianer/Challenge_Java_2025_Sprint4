package com.fiap.gestaofrota.controller;

import com.fiap.gestaofrota.entity.TelemetriaEntity;
import com.fiap.gestaofrota.service.TelemetriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/telemetria")
@RequiredArgsConstructor
public class TelemetriaController {

    private final TelemetriaService service;

    @PostMapping("/api")
    @ResponseBody
    public ResponseEntity<TelemetriaEntity> criar(@RequestBody TelemetriaEntity telemetria) {
        TelemetriaEntity salvo = service.salvar(telemetria);
        return new ResponseEntity<>(salvo, HttpStatus.CREATED);
    }

    @GetMapping("/api")
    @ResponseBody
    public ResponseEntity<List<TelemetriaEntity>> listar() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/api/device/{dispositivo}")
    @ResponseBody
    public ResponseEntity<List<TelemetriaEntity>> listarPorDispositivo(@PathVariable String dispositivo) {
        return ResponseEntity.ok(service.listarPorDispositivo(dispositivo));
    }

    @GetMapping("/lista")
    public String listaThymeleaf(Model model) {
        model.addAttribute("telemetrias", service.listarTodos());
        return "telemetria/list";
    }

}