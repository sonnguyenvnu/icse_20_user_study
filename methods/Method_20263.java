/** 
 * Detach the tracker
 * @param recyclerView The recycler view that the EpoxyController has its adapter added to.
 */
public void detach(@NonNull RecyclerView recyclerView){
  recyclerView.removeOnScrollListener(this.listener);
  recyclerView.removeOnLayoutChangeListener(this.listener);
  recyclerView.removeOnChildAttachStateChangeListener(this.listener);
  setTracker(recyclerView,null);
  attachedRecyclerView=null;
}
