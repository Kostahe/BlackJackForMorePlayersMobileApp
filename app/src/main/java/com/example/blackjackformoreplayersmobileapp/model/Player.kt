package com.example.blackjackformoreplayersmobileapp.model

class Player(var name: String, val id: Int) : Comparable<Player> {
    var sumaValueCards = 0
    val cardCollection = mutableListOf<Card>()


    override fun toString(): String {
        return "Player$id : $name with cards: $cardCollection suma of cards is $sumaValueCards"
    }

    override fun compareTo(other: Player): Int {
        return this.sumaValueCards - other.sumaValueCards
    }

    fun takeCard(cardsList: List<Card>) {
        val randomIndex = (Math.random() * cardsList.size).toInt()
        val card = cardsList[randomIndex]
        cardCollection.add(card)
        sumaValueCards += card.cardValue.value
        cardsList.elementAt(randomIndex)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Player

        if (name != other.name) return false
        if (id != other.id) return false
        if (sumaValueCards != other.sumaValueCards) return false
        if (cardCollection != other.cardCollection) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + id
        result = 31 * result + sumaValueCards
        result = 31 * result + cardCollection.hashCode()
        return result
    }
}