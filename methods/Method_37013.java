/** 
 * @param cell cell object
 * @return the card index of given cell object
 */
public int findCardIdxFor(C cell){
  int position=mData.indexOf(cell);
  return findCardIdxFor(position);
}
