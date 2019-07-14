/** 
 * @param position cell's adapter position
 * @return the card index of given cell's position
 */
public int findCardIdxFor(int position){
  Preconditions.checkState(mGroupBasicAdapter != null,"Must call bindView() first");
  return this.mGroupBasicAdapter.findCardIdxFor(position);
}
