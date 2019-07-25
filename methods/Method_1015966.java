/** 
 * Shuffles the discard pile and puts the cards under the cards remaining in the deck.
 */
public synchronized void reshuffle(){
  Collections.shuffle(discard);
  deck.addAll(0,discard);
  discard.clear();
}
