package com.tkdgus.springadvance.v0;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    @GetMapping()
    public String saveItem(String itemId) {
        orderService.orderItem(itemId);
        return "ok";
    }
}
