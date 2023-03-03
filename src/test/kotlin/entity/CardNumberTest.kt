package entity

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardNumberTest {
    @Test
    fun `플레이어의 카드의 합에 11을 더했을 때 21보다 크면 ACE가 1이다`() {
        // given
        val cardNumber = CardNumber.ACE

        // when
        val number = cardNumber.numberStrategy(12)

        // then
        assertThat(number).isEqualTo(1)
    }

    @Test
    fun `플레이어의 카드의 합에 11을 더했을 때 21보다 작으면 ACE가 11이다`() {
        // given
        val cardNumber = CardNumber.ACE

        // when
        val number = cardNumber.numberStrategy(3)

        // then
        assertThat(number).isEqualTo(11)
    }
}