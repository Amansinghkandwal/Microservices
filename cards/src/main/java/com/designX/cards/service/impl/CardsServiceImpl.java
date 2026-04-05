package com.designX.cards.service.impl;

import com.designX.cards.Exception.CardAlreadyExsistsException;
import com.designX.cards.Exception.NoResourceFoundException;
import com.designX.cards.constants.CardsConstants;
import com.designX.cards.dto.CardsDto;
import com.designX.cards.entity.Cards;
import com.designX.cards.mapper.CardsMapper;
import com.designX.cards.repository.CardsRepository;
import com.designX.cards.service.ICardsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class CardsServiceImpl implements ICardsService{

    private CardsRepository cardsRepository;

    /**
     * @param mobileNumber - mobileNumber of the customer
     */
    @Override
    public void createCard(String mobileNumber) {

          Optional<Cards> optionalLoan= cardsRepository.findByMobileNumber(mobileNumber);
        if(optionalLoan.isPresent())
        {

            throw new CardAlreadyExsistsException("Cards already registered with given mobileNumber"
                    +mobileNumber);
        }
          cardsRepository.save(createNewCard(mobileNumber));
    }

    private Cards createNewCard(String mobileNumber) {
        Cards newCard = new Cards();
        long randomCardNumber = 100000000000L + new Random().nextInt(900000000);
        newCard.setCardNumber(Long.toString(randomCardNumber));
        newCard.setMobileNumber(mobileNumber);
        newCard.setCardType(CardsConstants.CREDIT_CARD);
        newCard.setTotalLimit(CardsConstants.NEW_CARD_LIMIT);
        newCard.setAmountUsed(0);
        newCard.setAvailableAmount(CardsConstants.NEW_CARD_LIMIT);
        return newCard;
    }

    /**
     * @param mobileNumber - mobileNumber of the customer
     * @return - CardsDto - CardsDto Object
     */
    @Override
    public CardsDto fetchCard(String mobileNumber) {
        Cards card=cardsRepository.findByMobileNumber(mobileNumber).orElseThrow(
                ()->new NoResourceFoundException("Cards","mobileNumber",mobileNumber)
        );

        return CardsMapper.mapCardsToDto(card,new CardsDto());
    }

    /**
     * @param cardsDto
     * @return - true if updated successfully else false
     */
    @Override
    public boolean updateCard(CardsDto cardsDto) {
        String mobileNumber=cardsDto.getMobileNumber();
        Cards cards=cardsRepository.findByMobileNumber(mobileNumber).orElseThrow(
                ()->new NoResourceFoundException("Loan","mobileNumber",mobileNumber)
        );

        cards = CardsMapper.mapDtoToCard(cardsDto,cards);

        cardsRepository.save(cards);

        return true;
    }

    /**
     * @param mobileNumber
     * @return - true if deleted successfully else false
     */
    @Override
    public boolean deleteCard(String mobileNumber) {
        Cards cards=cardsRepository.findByMobileNumber(mobileNumber).orElseThrow(
                ()->new NoResourceFoundException("Loan","mobileNumber",mobileNumber)
        );

        cardsRepository.deleteById(cards.getCardId());
        return true;
    }
}
