/** 
 * @param cell cell object
 * @return the card index of given cell object
 */
public int findCardIdxFor(L cell){
  Preconditions.checkState(mGroupBasicAdapter != null,"Must call bindView() first");
  return this.mGroupBasicAdapter.findCardIdxFor(cell);
}
