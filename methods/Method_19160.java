/** 
 * Send the  {@link RecyclerCollectionComponent} a request to scroll the content to the giventarget position taking into account provided snapping behavior. The provided smoothScroller is used to scroll to the target.
 */
public void requestScrollToPositionWithSnap(final int target,final RecyclerView.SmoothScroller smoothScroller){
  requestScrollToPositionInner(true,target,target,smoothScroller);
}
