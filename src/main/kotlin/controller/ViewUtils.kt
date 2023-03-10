package controller

import entity.Card
import entity.CardNumber
import entity.CardType
import entity.Cards
import entity.GameResultType

class ViewUtils {
    companion object {
        private fun CardNumber.toShorthandString(): String {
            return when (this) {
                CardNumber.ACE -> "A"
                CardNumber.KING -> "K"
                CardNumber.QUEEN -> "Q"
                CardNumber.JACK -> "J"
                else -> this.numberStrategy(0).toString()
            }
        }

        private fun CardType.toKoreanString(): String {
            return when (this) {
                CardType.CLUB -> "♣️"
                CardType.HEART -> "❤️"
                CardType.SPADE -> "♠️"
                CardType.DIAMOND -> "♦️"
            }
        }

        fun Cards.toFormattedString(): String {
            return value.joinToString(", ") {
                it.toFormattedString()
            }
        }

        fun Card.toFormattedString(): String {
            return cardNumber.toShorthandString() + cardType.toKoreanString()
        }

        fun GameResultType.toKoreanString(): String {
            return when (this) {
                GameResultType.WIN -> "승"
                GameResultType.LOSE -> "패"
                GameResultType.DRAW -> "무"
            }
        }
    }
}
