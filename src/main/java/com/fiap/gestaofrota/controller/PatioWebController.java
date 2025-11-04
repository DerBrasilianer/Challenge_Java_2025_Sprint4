package com.fiap.gestaofrota.controller;

import com.fiap.gestaofrota.dto.PatioDTO;
import com.fiap.gestaofrota.entity.PatioEntity;
import com.fiap.gestaofrota.mapper.PatioMapper;
import com.fiap.gestaofrota.service.PatioService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/patios")
public class PatioWebController {

    private final PatioService patioService;

    public PatioWebController(PatioService patioService) {

        this.patioService = patioService;

    }

    @GetMapping
    public String listar(Model model) {

        model.addAttribute("patios", patioService.listarTodos().stream().map(PatioMapper::toPatioDTO).toList());

        return "patios/list";

    }

    @GetMapping("/novo")
    public String novo(Model model) {

        model.addAttribute("patio", new PatioDTO());

        return "patios/form";

    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute("patio") @Valid PatioDTO patioDto, BindingResult result) {

        if (result.hasErrors()) {

            return "patios/form";

        }

        PatioEntity entity = PatioMapper.toPatioEntity(patioDto);

        if (patioDto.getId() == null) {

            patioService.criar(entity);

        } else {

            patioService.atualizar(patioDto.getId(), entity);

        }

        return "redirect:/patios";

    }

    @GetMapping("/{id}/editar")
    public String editar(@PathVariable Long id, Model model) {

        PatioEntity entity = patioService.buscarPorId(id);
        model.addAttribute("patio", PatioMapper.toPatioDTO(entity));

        return "patios/form";

    }

    @PostMapping("/{id}/deletar")
    public String deletar(@PathVariable Long id) {

        patioService.deletar(id);

        return "redirect:/patios";

    }

}
