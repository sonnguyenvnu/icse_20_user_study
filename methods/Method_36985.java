/** 
 * Set original data list with type  {@link T} in Tangram.
 * @param data Original data with type {@link T}.
 */
public void setData(@Nullable T data){
  Preconditions.checkState(mGroupBasicAdapter != null,"Must call bindView() first");
  List<Card> cards=mDataParser.parseGroup(data,this);
  this.setData(cards);
}
