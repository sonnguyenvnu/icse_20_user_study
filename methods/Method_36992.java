/** 
 * @param type cell's type
 * @return last appearance position
 */
public int findLastPositionOfCell(String type){
  Preconditions.checkState(mGroupBasicAdapter != null,"Must call bindView() first");
  return this.mGroupBasicAdapter.findLastPositionOfCell(type);
}
