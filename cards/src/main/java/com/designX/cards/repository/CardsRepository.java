package com.designX.cards.repository;


import com.designX.cards.entity.Cards;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CardsRepository extends JpaRepository<Cards, Integer>{

    Optional<Cards> findByMobileNumber(String mobileNumber);
}
