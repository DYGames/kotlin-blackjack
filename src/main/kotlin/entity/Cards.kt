package entity

import misc.GameRule

class Cards(value: List<Card>) {
    private val _value: MutableList<Card> = value.toMutableList()
    val value: List<Card>
        get() = _value.toList()

    fun isBlackjack(): Boolean = value.size == User.BLACKJACK_COUNT && sumOfNumbers() == GameRule.WINNING_NUMBER

    fun sumOfNumbers(): Int = value.sumOf { it.cardNumber.numberStrategy(sumOfNumbersWithoutAce()) }

    private fun sumOfNumbersWithoutAce(): Int =
        value.filter { it.cardNumber != CardNumber.ACE }.sumOf { it.cardNumber.numberStrategy(0) }

    fun addCards(cards: Cards) {
        cards.value.forEach { _value.add(it) }
    }
}
