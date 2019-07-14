/** 
 * Remove a card at target card position. It cause full screen item's rebinding, be careful.
 * @param position the position of card in group
 */
@Deprecated public void removeData(int position){
  Preconditions.checkState(mGroupBasicAdapter != null,"Must call bindView() first");
  this.mGroupBasicAdapter.removeGroup(position);
}
