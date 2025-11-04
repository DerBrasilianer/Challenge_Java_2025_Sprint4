package com.fiap.gestaofrota.controller;

import com.fiap.gestaofrota.dto.MotoDTO;
import com.fiap.gestaofrota.entity.MotoEntity;
import com.fiap.gestaofrota.entity.PatioEntity;
import com.fiap.gestaofrota.enums.Marcas;
import com.fiap.gestaofrota.enums.Modelos;
import com.fiap.gestaofrota.mapper.MotoMapper;
import com.fiap.gestaofrota.service.MotoService;
import com.fiap.gestaofrota.service.PatioService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/motos")
public class MotoWebController {

    private final MotoService motoService;
    private final PatioService patioService;

    public MotoWebController(MotoService motoService, PatioService patioService) {

        this.motoService = motoService;
        this.patioService = patioService;

    }

    @GetMapping
    public String listar(@RequestParam(required = false) String placa, Model model) {

        var page = motoService.listar(Pageable.unpaged(), placa);
        List<MotoDTO> motos = page.getContent().stream().map(MotoMapper::toMotoDTO).toList();
        model.addAttribute("motos", motos);
        model.addAttribute("placa", placa);

        return "motos/list";

    }

    @GetMapping("/novo")
    public String novo(Model model) {

        model.addAttribute("moto", new MotoDTO());
        model.addAttribute("patios", patioService.listarTodos());
        model.addAttribute("marcas", Marcas.values());
        model.addAttribute("modelos", Modelos.values());

        return "motos/form";

    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute("moto") @Valid MotoDTO moto,
                         BindingResult result,
                         Model model) {

        if (result.hasErrors()) {

            model.addAttribute("patios", patioService.listarTodos());
            model.addAttribute("marcas", Marcas.values());
            model.addAttribute("modelos", Modelos.values());

            return "motos/form";

        }

        PatioEntity patio = patioService.buscarPorId(moto.getPatioId());
        MotoEntity entity = MotoMapper.toMotoEntity(moto, patio);

        if (moto.getId() == null) {

            motoService.criar(entity);

        } else {

            motoService.atualizar(moto.getId(), entity);

        }

        return "redirect:/motos";

    }

    @GetMapping("/{id}/editar")
    public String editar(@PathVariable Long id, Model model) {

        MotoEntity entity = motoService.buscarPorId(id);
        MotoDTO dto = MotoMapper.toMotoDTO(entity);
        model.addAttribute("moto", dto);
        model.addAttribute("patios", patioService.listarTodos());
        model.addAttribute("marcas", Marcas.values());
        model.addAttribute("modelos", Modelos.values());

        return "motos/form";

    }

    @PostMapping("/{id}/deletar")
    public String deletar(@PathVariable Long id) {

        motoService.deletar(id);

        return "redirect:/motos";

    }

}
