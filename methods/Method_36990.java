/** 
 * Remove the target card from list. It cause full screen item's rebinding, be careful.
 * @param data Target card
 */
@Deprecated public void removeData(Card data){
  Preconditions.checkState(mGroupBasicAdapter != null,"Must call bindView() first");
  this.mGroupBasicAdapter.removeGroup(data);
}
