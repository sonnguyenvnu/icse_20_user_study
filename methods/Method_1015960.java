/** 
 * Add a card to the discard pile.
 * @param card Card to add to discard pile.
 */
public synchronized void discard(final BlackCard card){
  if (card != null) {
    discard.add(card);
  }
}
