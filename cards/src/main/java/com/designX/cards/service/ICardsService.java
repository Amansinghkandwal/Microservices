package com.designX.cards.service;

import com.designX.cards.dto.CardsDto;

public interface ICardsService {

    /**
     *
     * @param mobileNumber - mobileNumber of the customer
     */
      void createCard(String mobileNumber);


    /**
     *
     * @param mobileNumber - mobileNumber of the customer
     * @return - CardsDto - CardsDto Object
     */
    CardsDto fetchCard(String mobileNumber);

    /**
     *
     * @param cardsDto
     * @return - true if updated successfully else false
     */
      boolean updateCard(CardsDto cardsDto);


    /**
     *
     * @param mobileNumber
     * @return - true if deleted successfully else false
     */
    boolean deleteCard(String mobileNumber);

}
