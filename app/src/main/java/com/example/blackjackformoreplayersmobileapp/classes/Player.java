package com.example.blackjackformoreplayersmobileapp.classes;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Player implements Comparable<Player> {
    private int sumaValue = 0;
    final private int id;
    private String name;
    private final ArrayList<Card> cardCollection = new ArrayList<>();

    public Player(String name, int id) {
        this.name = name;
        this.id = id;
    }
    public int getSumaValueCards() {
        return this.sumaValue;
    }
    public String getName() {
        return this.name;
    }
    public int getId() {
        return this.id;
    }
    public ArrayList<Card> getCardCollection() {
        return this.cardCollection;
    }
    public void setSumaValue(int sumaValue) {
        this.sumaValue = sumaValue;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Player" + this.id + " : " + this.name + " with cards: " + this.cardCollection + " suma of cards is " + this.sumaValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player that = (Player) o;
        return sumaValue == that.sumaValue && id == that.id && Objects.equals(name, that.name) && Objects.equals(cardCollection, that.cardCollection);
    }
    @Override
    public int compareTo(Player anotherPlayer) {
        return this.getSumaValueCards() - anotherPlayer.getSumaValueCards();
    }

    public void takeCard(List<Card> cardsList) {
        int randomIndex = (int) (Math.random() * cardsList.size());
        Card card = cardsList.get(randomIndex);
        this.cardCollection.add(card);
        this.sumaValue += card.getCardValue().getVALUE();
        cardsList.remove(randomIndex);
    }

}