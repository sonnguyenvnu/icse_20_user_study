/** 
 * {@inheritDoc}
 */
@Override protected void onTargetFound(View targetView,RecyclerView.State state,Action action){
  final int dx=calculateDxToMakeVisible(targetView,getHorizontalSnapPreference());
  final int dy=calculateDyToMakeVisible(targetView,getVerticalSnapPreference());
  final int distance=(int)Math.sqrt(dx * dx + dy * dy);
  final int time=calculateTimeForDeceleration(distance);
  if (time > 0) {
    action.update(-dx,-dy,time,mDecelerateInterpolator);
  }
}
