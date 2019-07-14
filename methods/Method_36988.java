/** 
 * Replace original data to Tangram at target position. It cause full screen item's rebinding, be careful.
 * @param position Target replace position.
 * @param data     Parsed data list.
 */
@Deprecated public void replaceData(int position,@Nullable List<Card> data){
  Preconditions.checkState(mGroupBasicAdapter != null,"Must call bindView() first");
  this.mGroupBasicAdapter.replaceGroup(position,data);
}
