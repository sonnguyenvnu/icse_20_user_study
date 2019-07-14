/** 
 * Replace original data to Tangram at target position. It cause full screen item's rebinding, be careful.
 * @param position Target insert position.
 * @param data     Original data with type {@link T}.
 */
@Deprecated public void replaceData(int position,@Nullable T data){
  Preconditions.checkState(mGroupBasicAdapter != null,"Must call bindView() first");
  replaceData(position,mDataParser.parseGroup(data,this));
}
