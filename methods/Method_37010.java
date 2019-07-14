/** 
 * @param card
 * @return card range of given instance
 */
public Range<Integer> getCardRange(Card card){
  if (card == null)   return Range.create(0,1);
  int idx=getGroups().indexOf(card);
  if (idx >= 0) {
    return mCards.get(idx).first;
  }
 else {
    return Range.create(0,1);
  }
}
