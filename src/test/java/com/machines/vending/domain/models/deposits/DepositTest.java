package com.machines.vending.domain.models.deposits;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

class DepositTest {

    @Test
    void shouldCreateEmptyDeposit() {
        // given
        final Integer buyerId = new Random().nextInt();

        // when
        final Deposit deposit = new Deposit(buyerId);

        // then
        assertThat(deposit.getBuyerId()).isEqualTo(buyerId);
        assertThat(deposit.getValue()).isZero();
    }

    @Test
    void shouldAddCoin() {
        //given
        final Integer buyerId = new Random().nextInt();
        final int five = Coin.FIVE.getValue();
        final int ten = Coin.TEN.getValue();
        final Deposit deposit = new Deposit(buyerId, ten);

        //when
        deposit.add(five);

        //then
        assertThat(deposit.getValue()).isEqualTo(15);
    }

    @Test
    void shouldReset() {
        //given
        final Integer buyerId = new Random().nextInt();
        final int five = Coin.FIVE.getValue();
        final Deposit deposit = new Deposit(buyerId, five);

        //when
        deposit.reset();

        //then
        assertThat(deposit.getValue()).isEqualTo(0);
    }
}
