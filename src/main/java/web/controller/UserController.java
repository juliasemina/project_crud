package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String searchUser() {
        return "users/list_users";
    }

    @GetMapping("get_user")
    public String getUser(@RequestParam(value = "id", required = false) Long id, ModelMap model) {
        model.addAttribute("user", userService.getUserbyId(id));
        return "users/get_user";
    }

    @GetMapping("/list_users")
    public String getUsers(Model model) {
        model.addAttribute("users", userService.getUsers());
        return "users/list_users";
    }

    @GetMapping("/add_user")
    public String newUser(@ModelAttribute("user") User user) {
        return "users/add_user";
    }

    @PostMapping()
    public String create(@ModelAttribute("user") User user) {
        userService.save(user);
        return "redirect:/users/list_users";
    }

    @GetMapping("/edit_user")
    public String edit(@RequestParam(value = "id", required = false) Long id, Model model) {
        model.addAttribute("user", userService.getUserbyId(id));
        return "users/edit_user";
    }

    @PatchMapping("/edit_user")
    public String edit(@ModelAttribute("user") User user) {
        userService.save(user);
        return "redirect:/users/list_users";
    }

    @GetMapping("/delete")
    public String deleteUser(@RequestParam(value = "id", required = false) Long id) {
        userService.delete(id);
        return "redirect:/users/list_users";
    }

}

