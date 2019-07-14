/** 
 * Append parsed data list with type  {@link C} in Tangram. It cause full screen item's rebinding, be careful.
 * @param data Parsed data list.
 */
@Deprecated public void appendData(@Nullable List<C> data){
  Preconditions.checkState(mGroupBasicAdapter != null,"Must call bindView() first");
  this.mGroupBasicAdapter.appendGroup(data);
}
