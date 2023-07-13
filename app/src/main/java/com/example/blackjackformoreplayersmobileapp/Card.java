package com.example.blackjackformoreplayersmobileapp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Card {
    private CardValue cardValue;
    private CardSuit cardSuit;

    public Card(CardValue cardValue, CardSuit cardSuit) {
        this.cardValue = cardValue;
        this.cardSuit = cardSuit;
    }

    public CardValue getCardValue() {
        return cardValue;
    }

    public void setCardValue(CardValue cardValue) {
        this.cardValue = cardValue;
    }

    public CardSuit getCardSuit() {
        return cardSuit;
    }

    public void setCardSuit(CardSuit cardSuit) {
        this.cardSuit = cardSuit;
    }

    @Override
    public String toString() {
        return this.cardSuit + " " + this.cardValue;
    }
    public static List<Card> generateCardList() {
        List<Card> cardsList = new ArrayList<>();
        List<CardValue> valuesList = new ArrayList<>();

        valuesList.add(CardValue.two);
        valuesList.add(CardValue.three);
        valuesList.add(CardValue.four);
        valuesList.add(CardValue.five);
        valuesList.add(CardValue.six);
        valuesList.add(CardValue.seven);
        valuesList.add(CardValue.eight);
        valuesList.add(CardValue.nine);
        valuesList.add(CardValue.ten);
        valuesList.add(CardValue.jack);
        valuesList.add(CardValue.queen);
        valuesList.add(CardValue.king);
        valuesList.add(CardValue.ace);

        for (int i = 0; i < 13; i++) {
            cardsList.add(new Card(valuesList.get(i), CardSuit.Hearts));
            cardsList.add(new Card(valuesList.get(i), CardSuit.Tiles));
            cardsList.add(new Card(valuesList.get(i), CardSuit.Clovers));
            cardsList.add(new Card(valuesList.get(i), CardSuit.Pikes));
        }
        Collections.shuffle(cardsList);
        return cardsList;
    }
}