package com.designX.cards.mapper;

import com.designX.cards.dto.CardsDto;
import com.designX.cards.entity.Cards;

public class CardsMapper{

    public static CardsDto mapCardsToDto(Cards cards,CardsDto cardsDto)
    {
        cardsDto.setCardNumber(cards.getCardNumber());
        cardsDto.setCardType(cards.getCardType());
        cardsDto.setTotalLimit(cards.getTotalLimit());
        cardsDto.setAmountUsed(cards.getAmountUsed());
        cardsDto.setAvailableAmount(cards.getAvailableAmount());
        cardsDto.setMobileNumber(cards.getMobileNumber());

        return cardsDto;
    }

    public static Cards mapDtoToCard(CardsDto cardsDto,Cards cards)
    {
        cards.setCardType(cardsDto.getCardType());
        cards.setTotalLimit(cardsDto.getTotalLimit());
        cards.setAmountUsed(cardsDto.getAmountUsed());
        cards.setAvailableAmount(cardsDto.getAvailableAmount());
        cards.setMobileNumber(cardsDto.getMobileNumber());
        cards.setCardNumber(cardsDto.getCardNumber());

        return cards;
    }
}
