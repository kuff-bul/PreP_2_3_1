package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import web.dao.UserDao;
import web.model.User;
import web.service.UserService;

import javax.validation.Valid;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String showAllUsers(ModelMap model) {
        List<User> allUsers = userService.getAllUsers();
        model.addAttribute("allUse", allUsers);
        return "all-users";
    }

    @GetMapping("/addNewUsers")
    public String addNewUser(ModelMap model) {
        User user = new User();
        model.addAttribute("user", user);
        return "add-user";
    }

    @PostMapping("/add-user")
    public String saveNewUser(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        System.out.println(user.toString());
        return "redirect:/";
    }

    @DeleteMapping("/{id}")
    public String delete(@RequestParam(value = "id") Long id) {
        userService.deleteUser(id);
        return "redirect:/";
    }

    @GetMapping("/{id}")
    public String editUser(@RequestParam(value = "id") Long id, ModelMap model) {
        System.out.println("Received ID: " + id);
        User user = userService.showId(id);
        model.addAttribute("user", user);
        return "edit-user";
    }

    @PatchMapping("/edit-user")
    public String updateUser(@Valid User user, BindingResult bindingResult) {
        userService.updateUser(user);
        System.out.println(user.toString());
        return "redirect:/";
    }

}
