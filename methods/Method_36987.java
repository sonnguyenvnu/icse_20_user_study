/** 
 * Insert parsed data to Tangram at target position. It cause full screen item's rebinding, be careful.
 * @param position Target insert position.
 * @param data     Parsed data list.
 */
@Deprecated public void insertData(int position,@Nullable List<Card> data){
  Preconditions.checkState(mGroupBasicAdapter != null,"Must call bindView() first");
  this.mGroupBasicAdapter.insertGroup(position,data);
}
