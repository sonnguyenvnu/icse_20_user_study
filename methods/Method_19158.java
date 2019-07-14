/** 
 * Send the  {@link RecyclerCollectionComponent} a request to scroll the content to the next itemin the binder.
 * @param animated whether the scroll will happen with animation.
 */
public void requestScrollToNextPosition(boolean animated){
  final int defaultTarget=Math.max(0,mLastCompletelyVisibleItemPosition + 1);
  final int snapTarget=getSmoothScrollTarget(true,defaultTarget);
  requestScrollToPositionInner(animated,defaultTarget,snapTarget);
}
