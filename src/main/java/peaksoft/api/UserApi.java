package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.entity.User;
import peaksoft.service.UserService;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserApi {
    private final UserService userService;


    @GetMapping
    public String getAllUsers(Model model){
        model.addAttribute("users",userService.getAllUsers());
        return "getAllUsers";
    }

    @GetMapping("/new")
    public String createUser(Model model){
        model.addAttribute("newUser",new User());
        return "newUser";
    }

    @PostMapping("/save")
    public String saveUser(@ModelAttribute ("newUser") User user){
        userService.saveUser(user);
        return "redirect:/users";
    }

//    @GetMapping("/{id}/getUser")
//    public String getUserToUpdateById(@PathVariable Long id,Model model){
//        model.addAttribute("user",userService.getUserById(id));
//        return "updateUser";
//    }

    @GetMapping("/{email}/getUser")
    public String getUserToUpdateByEmail(@PathVariable String email,Model model){
        model.addAttribute("user",userService.getUserByEmail(email));
        return "updateUser";
    }

    @PostMapping("/{id}/update")
    public String updateUser(@PathVariable Long id,
                             @ModelAttribute ("user") User user){
        userService.updateUser(id,user);
        return "redirect:/users";
    }

    @GetMapping("/{id}/delete")
    public String deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return "redirect:/users";
    }




}
