/** 
 * {@inheritDoc} 
 */
@Override public void onMoved(int fromPosition,int toPosition){
  mAdapter.notifyItemMoved(fromPosition,toPosition);
}
