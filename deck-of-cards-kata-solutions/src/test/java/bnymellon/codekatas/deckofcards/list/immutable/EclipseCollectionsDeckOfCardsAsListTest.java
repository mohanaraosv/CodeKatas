/*
 * Copyright 2022 The Bank of New York Mellon.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package bnymellon.codekatas.deckofcards.list.immutable;

import java.util.Random;

import bnymellon.codekatas.deckofcards.Rank;
import bnymellon.codekatas.deckofcards.Suit;
import org.eclipse.collections.api.factory.Bags;
import org.eclipse.collections.impl.tuple.primitive.PrimitiveTuples;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EclipseCollectionsDeckOfCardsAsListTest
{
    private EclipseCollectionsDeckOfCardsAsList ecDeck = new EclipseCollectionsDeckOfCardsAsList();
    private JDKImperativeDeckOfCardsAsList jdkDeck = new JDKImperativeDeckOfCardsAsList();

    @Test
    public void allCards()
    {
        Assertions.assertEquals(this.jdkDeck.getCards(), this.ecDeck.cards());
    }

    @Test
    public void cardsAreImmutable()
    {
        var ecCards = this.ecDeck.cards().castToList();
        Assertions.assertThrows(
                UnsupportedOperationException.class,
                () -> ecCards.remove(0));
        Assertions.assertThrows(
                UnsupportedOperationException.class,
                ecCards::clear);
        Assertions.assertThrows(
                UnsupportedOperationException.class,
                () -> ecCards.add(null));
    }

    @Test
    public void diamonds()
    {
        Assertions.assertEquals("|A♦|,|2♦|,|3♦|,|4♦|,|5♦|,|6♦|,|7♦|,|8♦|,|9♦|,|10♦|,|J♦|,|Q♦|,|K♦|",
                this.ecDeck.diamonds().makeString(","));
    }

    @Test
    public void hearts()
    {
        Assertions.assertEquals("|A♥|,|2♥|,|3♥|,|4♥|,|5♥|,|6♥|,|7♥|,|8♥|,|9♥|,|10♥|,|J♥|,|Q♥|,|K♥|",
                this.ecDeck.hearts().makeString(","));
    }

    @Test
    public void spades()
    {
        Assertions.assertEquals("|A♠|,|2♠|,|3♠|,|4♠|,|5♠|,|6♠|,|7♠|,|8♠|,|9♠|,|10♠|,|J♠|,|Q♠|,|K♠|",
                this.ecDeck.spades().makeString(","));
    }

    @Test
    public void clubs()
    {
        Assertions.assertEquals("|A♣|,|2♣|,|3♣|,|4♣|,|5♣|,|6♣|,|7♣|,|8♣|,|9♣|,|10♣|,|J♣|,|Q♣|,|K♣|",
                this.ecDeck.clubs().makeString(","));
    }

    @Test
    public void deal()
    {
        var ecShuffle = this.ecDeck.shuffle(new Random(1));
        var jdkShuffle = this.jdkDeck.shuffle(new Random(1));

        var ecHand = this.ecDeck.deal(ecShuffle, 5);
        var jdkHand = this.jdkDeck.deal(jdkShuffle, 5);
        Assertions.assertEquals(jdkHand, ecHand);
    }

    @Test
    public void shuffleAndDealHands()
    {
        var ecHands = this.ecDeck.shuffleAndDeal(new Random(1), 5, 5);
        var jdkHands = this.jdkDeck.shuffleAndDeal(new Random(1), 5, 5);
        Assertions.assertEquals(jdkHands, ecHands);
    }

    @Test
    public void dealHands()
    {
        var ecShuffled = this.ecDeck.shuffle(new Random(1));
        var jdkShuffled = this.jdkDeck.shuffle(new Random(1));
        var ecHands = this.ecDeck.dealHands(ecShuffled, 5, 5);
        var jdkHands = this.jdkDeck.dealHands(jdkShuffled, 5, 5);
        Assertions.assertEquals(jdkHands, ecHands);
    }

    @Test
    public void cardsBySuit()
    {
        var ecCardsBySuit = this.ecDeck.cardsBySuit();
        Assertions.assertEquals(13, ecCardsBySuit.get(Suit.CLUBS).size());
        Assertions.assertEquals("|A♣|,|2♣|,|3♣|,|4♣|,|5♣|,|6♣|,|7♣|,|8♣|,|9♣|,|10♣|,|J♣|,|Q♣|,|K♣|" ,
                this.ecDeck.cardsBySuit().get(Suit.CLUBS).makeString(","));
        Assertions.assertEquals("|A♦|,|2♦|,|3♦|,|4♦|,|5♦|,|6♦|,|7♦|,|8♦|,|9♦|,|10♦|,|J♦|,|Q♦|,|K♦|" ,
                this.ecDeck.cardsBySuit().get(Suit.DIAMONDS).makeString(","));
        Assertions.assertEquals("|A♠|,|2♠|,|3♠|,|4♠|,|5♠|,|6♠|,|7♠|,|8♠|,|9♠|,|10♠|,|J♠|,|Q♠|,|K♠|" ,
                this.ecDeck.cardsBySuit().get(Suit.SPADES).makeString(","));
        Assertions.assertEquals("|A♥|,|2♥|,|3♥|,|4♥|,|5♥|,|6♥|,|7♥|,|8♥|,|9♥|,|10♥|,|J♥|,|Q♥|,|K♥|" ,
                this.ecDeck.cardsBySuit().get(Suit.HEARTS).makeString(","));

    }

    @Test
    public void cardsBySuitIsImmutable()
    {
        var ecCardsBySuit = this.ecDeck.cardsBySuit();
        Assertions.assertThrows(
                UnsupportedOperationException.class,
                () -> ecCardsBySuit.get(Suit.CLUBS).castToList().remove(0));
        Assertions.assertThrows(
                UnsupportedOperationException.class,
                () -> ecCardsBySuit.get(Suit.CLUBS).castToList().add(null));
        Assertions.assertThrows(
                UnsupportedOperationException.class,
                ecCardsBySuit.get(Suit.CLUBS).castToList()::clear);
    }

    @Test
    public void countsBySuit()
    {
        Assertions.assertEquals(
                Bags.mutable.withOccurrences(
                        PrimitiveTuples.pair("♥", 13),
                        PrimitiveTuples.pair("♣", 13),
                        PrimitiveTuples.pair("♦", 13),
                        PrimitiveTuples.pair("♠", 13)),
                this.ecDeck.countsBySuit().collect(Suit::toString));
    }

    @Test
    public void countsByRank()
    {
        Assertions.assertEquals(
                Bags.mutable.withOccurrences(
                        PrimitiveTuples.pair("A", 4),
                        PrimitiveTuples.pair("Q", 4),
                        PrimitiveTuples.pair("K", 4),
                        PrimitiveTuples.pair("3", 4),
                        PrimitiveTuples.pair("2", 4),
                        PrimitiveTuples.pair("J", 4),
                        PrimitiveTuples.pair("10", 4),
                        PrimitiveTuples.pair("7", 4),
                        PrimitiveTuples.pair("6", 4),
                        PrimitiveTuples.pair("5", 4),
                        PrimitiveTuples.pair("4", 4),
                        PrimitiveTuples.pair("9", 4),
                        PrimitiveTuples.pair("8", 4)),
                this.ecDeck.countsByRank().collect(Rank::toString));
    }
}
