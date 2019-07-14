/** 
 * @return true if there are more items in the data adapter
 */
boolean hasMore(RecyclerView.State state){
  return mCurrentPosition >= 0 && mCurrentPosition < state.getItemCount();
}
