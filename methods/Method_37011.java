/** 
 * @param card
 * @return card index of given instance
 */
public int findCardIdxForCard(L card){
  for (int i=0, size=mCards.size(); i < size; i++) {
    if (mCards.get(i).second == card) {
      return i;
    }
  }
  return -1;
}
