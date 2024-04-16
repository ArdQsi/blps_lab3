package com.webapp.controllers;

import com.webapp.dto.CardDto;
import com.webapp.dto.MessageDto;
import com.webapp.dto.UserIdDto;
import com.webapp.service.CardService;
import com.webapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rutube.ru")
public class PaymentController {
    private final CardService cardService;
    private final UserService userService;

    @PostMapping("/cards")
    public MessageDto saveCard(@RequestBody CardDto cardDto){
        return cardService.saveCard(cardDto);
    }

    @PostMapping("/subscription")
    public MessageDto updateSubscription(@RequestBody UserIdDto userIdDto){
        return userService.updateSubscription(userIdDto.getUserId());
    }
}
