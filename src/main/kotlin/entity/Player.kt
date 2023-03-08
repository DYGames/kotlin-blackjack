package entity

import model.BlackjackStage
import model.CardFactory

class Player(val name: Name, private val bet: Money, val cards: Cards = Cards(listOf())) : User {
    override fun isDistributable(): Boolean = cards.sumOfNumbers() < BlackjackStage.WINNING_NUMBER

    fun addMoreCards(cardFactory: CardFactory) {
        cards.addCards(cardFactory.generate(User.SINGLE_DISTRIBUTE_COUNT))
    }

    fun determineGameResult(dealerCardNumberSum: Int): GameResultType {
        val playerCardNumberSum = cards.sumOfNumbers()
        return when {
            isWin(playerCardNumberSum, dealerCardNumberSum) -> GameResultType.WIN
            isDraw(playerCardNumberSum, dealerCardNumberSum) -> GameResultType.DRAW
            else -> GameResultType.LOSE
        }
    }

    fun calculateWinMoney(gameResultType: GameResultType): Money {
        if (cards.isBlackjack()) return Money((bet.value * 1.5).toInt())
        return when (gameResultType) {
            GameResultType.WIN -> Money(bet.value * 2)
            GameResultType.DRAW -> Money(0)
            GameResultType.LOSE -> Money(-bet.value)
        }
    }

    private fun isWin(playerCardNumberSum: Int, dealerCardNumberSum: Int): Boolean =
        playerCardNumberSum in (dealerCardNumberSum + 1)..BlackjackStage.WINNING_NUMBER || BlackjackStage.WINNING_NUMBER in playerCardNumberSum until dealerCardNumberSum

    private fun isDraw(playerCardNumberSum: Int, dealerCardNumberSum: Int): Boolean =
        playerCardNumberSum == dealerCardNumberSum || playerCardNumberSum > BlackjackStage.WINNING_NUMBER && dealerCardNumberSum > BlackjackStage.WINNING_NUMBER
}
