package com.profi.profi;

import com.profi.profi.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.profi.profi.repository.UserRepository;

import java.util.List;

@Controller
public class WebController {

    @Autowired
    UserRepository repository;

    @GetMapping("/")
    public String showUserList(@RequestParam(name="email", required = false, defaultValue ="")String email,
                               @RequestParam(name="name1", required = false, defaultValue ="")String name1,
                               @RequestParam(name="surname", required = false, defaultValue ="")String surname,
                               @RequestParam(name="id1", required = false, defaultValue ="")Long id1,
                               @RequestParam(name="name2", required = false, defaultValue ="")String name2,
                               @RequestParam(name="name3", required = false, defaultValue ="")String name3,
                               @RequestParam(name="email3", required = false, defaultValue ="")String email3,
                               @RequestParam(name="name4", required = false, defaultValue ="")String name4,
                               @RequestParam(name="email2", required = false, defaultValue ="")String email2,
                               @RequestParam(name="name5", required = false, defaultValue ="")String
                                           name5,Model model)
    {
        List<User> users = repository.findAll();
        if(!email.isEmpty())
        {
            users = repository.findByEmailEndsWith(email);
        }
        else if(!email2.isEmpty())
        {
            users = repository.findSomeEmail(email2);
        }
        else if(!email3.isEmpty())
        {
            users = repository.findByEmailNotContaining(email3);
        }
        else if(!name1.isEmpty())
        {
            users = repository.findTop2ByNameStartsWith(name1);
        }
        else if(!name2.isEmpty())
        {
            users = repository.findLastTwoRows(name2);
        }
        else if(!name3.isEmpty())
        {
            users = repository.SortByName(name3);
        }
        else if(!name4.isEmpty())
        {
            users = repository.EqualNameSurname(name4);
        }
        else if(!name5.isEmpty())
        {
            users = repository.findDistinctByName(name5);
        }
        else if(!surname.isEmpty())
        {
            users = repository.findBySurnameContaining(surname);
        }
        else if(id1!=null)
        {
            users = repository.SortUsersById();
        }
        model.addAttribute("users", users);
        return "index";
    }
    @PostMapping("/adduser")
    public String createUser(@ModelAttribute User user){
        addUser(user);
        return "redirect:/";
    }
    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable("id") long id, User user) {
        updateUser(user);
        return "redirect:/";
    }
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        deleteById(id);
        return "redirect:/";
    }
    @GetMapping("/signup")
    public String showSignUpForm(User user) {
        return "add-user";
    }
    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        User user = repository.getById(id);
        model.addAttribute("user", user);
        return "update-user";
    }
    private void deleteById(long id) {
        repository.deleteById(id);
    }
    private void addUser(User newUser) {
        repository.save(newUser);
    }
    private void updateUser(User updateUser) {
        User oldUser = repository.getById(updateUser.getId());
        oldUser.setName(updateUser.getName());
        oldUser.setSurname(updateUser.getSurname());
        oldUser.setEmail(updateUser.getEmail());
        repository.save(oldUser);
    }
}