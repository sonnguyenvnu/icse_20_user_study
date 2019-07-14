/** 
 * Send the  {@link RecyclerCollectionComponent} a request to scroll the content to the previousitem in the binder.
 * @param animated whether the scroll will happen with animation.
 */
public void requestScrollToPreviousPosition(boolean animated){
  final int defaultTarget=Math.max(0,mFirstCompletelyVisibleItemPosition - 1);
  final int snapTarget=getSmoothScrollTarget(false,defaultTarget);
  requestScrollToPositionInner(animated,defaultTarget,snapTarget);
}
