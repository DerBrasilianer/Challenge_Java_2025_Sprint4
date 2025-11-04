package com.fiap.gestaofrota.controller;

import com.fiap.gestaofrota.dto.UserDTO;
import com.fiap.gestaofrota.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterController {

    private final UserService userService;

    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("userDTO", new UserDTO());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("userDTO") UserDTO userDTO,
                               BindingResult result,
                               Model model) {

        if (result.hasErrors()) {
            return "register";
        }

        if (userService.existsByUsername(userDTO.getUsername())) {
            result.rejectValue("username", "error.userDTO", "Nome de usuário já existe");
            return "register";
        }

        // Se role não for selecionada, definir como USER por padrão
        if (userDTO.getRole() == null) {
            userDTO.setRole(com.fiap.gestaofrota.enums.Role.USER);
        }

        userService.registerNewUser(userDTO);
        model.addAttribute("successMessage", "Cadastro realizado com sucesso! Faça login.");

        return "login";
    }

}