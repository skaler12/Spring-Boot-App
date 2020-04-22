package com.spring.boot.application.controller;

import com.spring.boot.application.UsernameOrIdNotFound;
import com.spring.boot.application.dto.ChangePasswordForm;
import com.spring.boot.application.entity.Role;
import com.spring.boot.application.entity.User;
import com.spring.boot.application.exception.CustomeFieldValidationException;
import com.spring.boot.application.repository.RoleRepository;
import com.spring.boot.application.service.UserService;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    RoleRepository roleRepository;

    @GetMapping("/")
    public String start(){
        return "start";
    }
    //podstawowa strona na domyslnym endpoincie
    @GetMapping({"/login"})
    public String index() {
        return "index";
    }
    @GetMapping("/signup")
    public String signup(Model model) {
        Role userRole = roleRepository.findByName("USER");
        List<Role> roles = Arrays.asList(userRole);

        model.addAttribute("signup",true);
        model.addAttribute("userForm", new User());
        model.addAttribute("roles",roles);
        return "user-form/user-signup";
    }

    @PostMapping("/signup")
    public String signupAction(@Valid @ModelAttribute("userForm")User user, BindingResult result, ModelMap model) {
        Role userRole = roleRepository.findByName("USER");
        List<Role> roles = Arrays.asList(userRole);
        model.addAttribute("userForm", user);
        model.addAttribute("roles",roles);
        model.addAttribute("signup",true);

        if(result.hasErrors()) {
            return "user-form/user-signup";
        }else {
            try {
                userService.createUser(user);
            } catch (CustomeFieldValidationException cfve) {
                result.rejectValue(cfve.getFieldName(), null, cfve.getMessage());
            }catch (Exception e) {
                model.addAttribute("formErrorMessage",e.getMessage());
            }
        }
        return index();
    }
    //wszystkie funkcjonalnosci jakie sa potrzebne do wyswietlenia formularza usera
    @GetMapping("/userForm")
    public String userForm(Model model) {
        model.addAttribute("userForm",new User());
        model.addAttribute("userList",userService.getAllUsers());
        model.addAttribute("roles",roleRepository.findAll());
        model.addAttribute("listTab","active");
        return "user-form/user-view";
    }

    @PostMapping("/userForm")
    public String createUser(@Valid @ModelAttribute("userForm")User user, BindingResult result, ModelMap model) {
        //jezeli result error to wyswietl formularz
        if(result.hasErrors()) {
            model.addAttribute("userForm", user);
            model.addAttribute("formTab","active");
        }else {
            try {
                //sprobuj stworzyc nowego uzytkownika
                userService.createUser(user);
                model.addAttribute("userForm", new User());
                model.addAttribute("listTab","active");

            } catch (CustomeFieldValidationException cfve) {
                result.rejectValue(cfve.getFieldName(), null, cfve.getMessage());
                model.addAttribute("userForm", user);
                model.addAttribute("formTab","active");
                model.addAttribute("userList", userService.getAllUsers());
                model.addAttribute("roles",roleRepository.findAll());
            }catch (Exception e) {
                //jezeli cos nie tak to rzuc wyjatek
                model.addAttribute("formErrorMessage",e.getMessage());
                model.addAttribute  ("userForm", user);
                model.addAttribute("formTab","active");
                model.addAttribute("userList", userService.getAllUsers());
                model.addAttribute("roles",roleRepository.findAll());
            }
        }
        //pokaz po dodania user list
        model.addAttribute("userList", userService.getAllUsers());
        model.addAttribute("roles",roleRepository.findAll());
        return "user-form/user-view";
    }
    //edytuj usera po id, wyszukaj do edycji
    @GetMapping("/editUser/{id}")
    public String getEditUserForm(Model model, @PathVariable(name ="id")Long id)throws Exception{
        User userToEdit = userService.getUserById(id);

        model.addAttribute("userForm", userToEdit);
        model.addAttribute("userList", userService.getAllUsers());
        model.addAttribute("roles",roleRepository.findAll());
        model.addAttribute("formTab","active");
        model.addAttribute("editMode","true");
        model.addAttribute("passwordForm",new ChangePasswordForm(id));

        return "user-form/user-view";
    }
    //proba edycji metoda post
    @PostMapping("/editUser")
    public String postEditUserForm(@Valid @ModelAttribute("userForm")User user, BindingResult result, ModelMap model) {
        if(result.hasErrors()) {
            //jezeli cos nie tak to wyswietl formularz
            model.addAttribute("userForm", user);
            model.addAttribute("formTab","active");
            model.addAttribute("editMode","true");
            model.addAttribute("passwordForm",new ChangePasswordForm(user.getId()));
        }else {
            try {
                //sprobuj edytowac,updatetowac usera jezeli ok
                userService.updateUser(user);
                model.addAttribute("userForm", new User());
                model.addAttribute("listTab","active");
            } catch (Exception e) {
                //w innym wypadku rzuc wyjatek
                model.addAttribute("formErrorMessage",e.getMessage());
                model.addAttribute("userForm", user);
                model.addAttribute("formTab","active");
                model.addAttribute("userList", userService.getAllUsers());
                model.addAttribute("roles",roleRepository.findAll());
                model.addAttribute("editMode","true");
                model.addAttribute("passwordForm",new ChangePasswordForm(user.getId()));
            }
        }
        //po wysztskim wysietl liste userow
        model.addAttribute("userList", userService.getAllUsers());
        model.addAttribute("roles",roleRepository.findAll());
        return "user-form/user-view";

    }
    //powrot z formularza do listy jezeli nie chcemy jednak edytowac usera
    @GetMapping("/userForm/cancel")
    public String cancelEditUser(ModelMap model) {
        return "redirect:/userForm";
    }
    //wyszukanie po id i usuniecie konkretnego usera
    @GetMapping("/deleteUser/{id}")
    public String deleteUser(Model model, @PathVariable(name="id")Long id) throws Exception {
        try {
            userService.deleteUser(id);
        } catch (UsernameOrIdNotFound uoin) {
            model.addAttribute("listErrorMessage",uoin.getMessage());
        }
        return userForm(model);
    }
    @PostMapping("/editUser/changePassword")
    public ResponseEntity postEditUseChangePassword(@Valid @RequestBody ChangePasswordForm form, Errors errors) {
        try {
            if( errors.hasErrors()) {
                String result = errors.getAllErrors()
                        .stream().map(x -> x.getDefaultMessage())
                        .collect(Collectors.joining(""));

                throw new Exception(result);
            }
            userService.changePassword(form);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok("Success");
    }
}