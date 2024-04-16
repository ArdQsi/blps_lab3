package com.webapp.service;

import com.webapp.dto.CardDto;
import com.webapp.dto.MessageDto;
import com.webapp.exceptioin.ResourceAlreadyExistsException;
import com.webapp.exceptioin.ResourceNotFoundException;
import com.webapp.model.CardEntity;
import com.webapp.model.UserEntity;
import com.webapp.repository.CardRepository;
import com.webapp.repository.UserRepository;
import com.webapp.repository.UserXmlRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CardService {
    private final CardRepository cardRepository;
    private final UserRepository userRepository;
    private final UserService userService;

    public MessageDto saveCard(CardDto cardDto){

        if(!checkCard(cardDto)){
            throw new ResourceNotFoundException("Incorrect card data");
        }

        if(cardDto.getAmount()<0){
            throw new ResourceNotFoundException("Incorrect amount of money");
        }

        UserEntity userEntity = userRepository.findUserById(cardDto.getUserId());

        if(userEntity==null){
            throw new ResourceNotFoundException("Incorrect user id");
        }

        CardEntity card = new CardEntity();
        card.setNumber(cardDto.getNumber());
        card.setMonth(cardDto.getMonth());
        card.setYear(cardDto.getYear());
        card.setName(cardDto.getName());
        card.setSurname(cardDto.getSurname());
        card.setUser(userEntity);

        cardRepository.save(card);
        userService.updateBalance(userEntity, cardDto.getAmount());
        return new MessageDto("Payment was successful");
    }

    private boolean checkCard(CardDto cardDto){
        LocalDate date = LocalDate.now();
        String regex_cardNumber = "(^$|[0-9]{16})";
        String regex_cardCVC = "(^$|[0-9]{3})";

        if (!cardDto.getNumber().matches(regex_cardNumber)) {
            return false;
        }
        if (!cardDto.getCvc().toString().matches(regex_cardCVC)) {
            return false;
        }
        if(!(cardDto.getMonth()<=12 && cardDto.getMonth()>=1)){
            return false;
        }
        if(cardDto.getYear()<date.getYear()){
            return false;
        }
        if(cardDto.getYear()==date.getYear() && cardDto.getMonth()<date.getMonthValue()){
            return false;
        }
        return true;
    }

    public List<CardEntity> getCardById(Long id){
        List<Long> ids = List.of(id);
        return cardRepository.findAllById(ids);
    }
}
