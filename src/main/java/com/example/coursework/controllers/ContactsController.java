package com.example.coursework.controllers;

import com.example.coursework.services.EmailService;
import com.example.coursework.models.Order;
import com.example.coursework.models.repositories.OrderRepository;
import com.example.coursework.models.repositories.UserRepository;
import com.example.coursework.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping
public class ContactsController {

    @Autowired
    OrderService orderService;

    @GetMapping("/contacts")
    public String getContacts(Model model, @AuthenticationPrincipal UserDetails currentUser) {
        Order newOrder = new Order();
        List<Order> orders = orderService.getAllOrdersByLogin(currentUser.getUsername());

        model.addAttribute("newOrder", newOrder);
        model.addAttribute("orders", orders);

        return "contacts";
    }

    @GetMapping("/addOrder")
    public String order(Model model) {

        return "contacts";
    }

    @PostMapping("/addOrder")
    public String addOrder(@ModelAttribute("newOrder") Order newOrder,
                           @AuthenticationPrincipal UserDetails currentUser) {
        orderService.addNewOrder(newOrder, currentUser.getUsername());

        return "redirect:contacts";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/admin")
    public String admin(Model model, @AuthenticationPrincipal UserDetails currentUser) {
        Order newOrder = new Order();
        List<Order> orders = orderService.getAll();

        model.addAttribute("newOrder", newOrder);
        model.addAttribute("orders", orders);

        return "admin";
    }

    @GetMapping("/order")
    public String delOrder(Model model, @RequestParam(value="id") Long id) {
        orderService.delOrderById(id);

        return "redirect:/contacts";
    }
}

