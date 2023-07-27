package com.example.blackjackformoreplayersmobileapp.model

import androidx.annotation.DrawableRes
import com.example.blackjackformoreplayersmobileapp.R


class Card(var cardValue: CardValue, private var cardSuit: CardSuit) {

    override fun toString(): String {
        return "$cardSuit $cardValue"
    }

    @DrawableRes
    val cardImage = when (cardSuit) {
        CardSuit.Hearts -> when (cardValue) {
            CardValue.two -> R.drawable.hearts_2
            CardValue.three -> R.drawable.hearts_3
            CardValue.four -> R.drawable.hearts_4
            CardValue.five -> R.drawable.hearts_5
            CardValue.six -> R.drawable.hearts_6
            CardValue.seven -> R.drawable.hearts_7
            CardValue.eight -> R.drawable.hearts_8
            CardValue.nine -> R.drawable.hearts_9
            CardValue.ten -> R.drawable.hearts_10
            CardValue.jack -> R.drawable.hearts_jack
            CardValue.queen -> R.drawable.hearts_queen
            CardValue.king -> R.drawable.hearts_king
            CardValue.ace -> R.drawable.hearts_ace
        }

        CardSuit.Clovers -> when (cardValue) {
            CardValue.two -> R.drawable.clovers_2
            CardValue.three -> R.drawable.clovers_3
            CardValue.four -> R.drawable.clovers_4
            CardValue.five -> R.drawable.clovers_5
            CardValue.six -> R.drawable.clovers_6
            CardValue.seven -> R.drawable.clovers_7
            CardValue.eight -> R.drawable.clovers_8
            CardValue.nine -> R.drawable.clovers_9
            CardValue.ten -> R.drawable.clovers_10
            CardValue.jack -> R.drawable.clovers_jack
            CardValue.queen -> R.drawable.clovers_queen
            CardValue.king -> R.drawable.clovers_king
            CardValue.ace -> R.drawable.clovers_ace
        }

        CardSuit.Pikes -> when (cardValue) {
            CardValue.two -> R.drawable.pikes_2
            CardValue.three -> R.drawable.pikes_3
            CardValue.four -> R.drawable.pikes_4
            CardValue.five -> R.drawable.pikes_5
            CardValue.six -> R.drawable.pikes_6
            CardValue.seven -> R.drawable.pikes_7
            CardValue.eight -> R.drawable.pikes_8
            CardValue.nine -> R.drawable.pikes_9
            CardValue.ten -> R.drawable.pikes_10
            CardValue.jack -> R.drawable.pikes_jack
            CardValue.queen -> R.drawable.pikes_queen
            CardValue.king -> R.drawable.pikes_king
            CardValue.ace -> R.drawable.pikes_ace
        }

        CardSuit.Tiles -> when (cardValue) {
            CardValue.two -> R.drawable.tiles_2
            CardValue.three -> R.drawable.tiles_3
            CardValue.four -> R.drawable.tiles_4
            CardValue.five -> R.drawable.tiles_5
            CardValue.six -> R.drawable.tiles_6
            CardValue.seven -> R.drawable.tiles_7
            CardValue.eight -> R.drawable.tiles_8
            CardValue.nine -> R.drawable.tiles_9
            CardValue.ten -> R.drawable.tiles_10
            CardValue.jack -> R.drawable.tiles_jack
            CardValue.queen -> R.drawable.tiles_queen
            CardValue.king -> R.drawable.tiles_king
            CardValue.ace -> R.drawable.tiles_ace
        }
    }

    companion object {
        fun generateCardList(): MutableList<Card> {
            val cardsList: MutableList<Card> = ArrayList()
            val valuesList: MutableList<CardValue> = ArrayList()
            valuesList.add(CardValue.two)
            valuesList.add(CardValue.three)
            valuesList.add(CardValue.four)
            valuesList.add(CardValue.five)
            valuesList.add(CardValue.six)
            valuesList.add(CardValue.seven)
            valuesList.add(CardValue.eight)
            valuesList.add(CardValue.nine)
            valuesList.add(CardValue.ten)
            valuesList.add(CardValue.jack)
            valuesList.add(CardValue.queen)
            valuesList.add(CardValue.king)
            valuesList.add(CardValue.ace)
            for (i in 0..12) {
                cardsList.add(Card(valuesList[i], CardSuit.Hearts))
                cardsList.add(Card(valuesList[i], CardSuit.Tiles))
                cardsList.add(Card(valuesList[i], CardSuit.Clovers))
                cardsList.add(Card(valuesList[i], CardSuit.Pikes))
            }
            cardsList.shuffle()
            return cardsList
        }
    }
}