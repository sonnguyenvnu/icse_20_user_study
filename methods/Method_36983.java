/** 
 * Append original data with type  {@link T} to Tangram. It cause full screen item's rebinding, be careful.
 * @param data Original data with type {@link T}.
 */
@Deprecated public void appendData(@Nullable T data){
  Preconditions.checkState(mGroupBasicAdapter != null,"Must call bindView() first");
  appendData(mDataParser.parseGroup(data,this));
}
