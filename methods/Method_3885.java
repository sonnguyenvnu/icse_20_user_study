@Override protected void onTargetFound(View targetView,RecyclerView.State state,Action action){
  final int dy=calculateDyToMakeVisible(targetView);
  final int time=calculateTimeForDeceleration(dy);
  if (time > 0) {
    action.update(0,-dy,Math.max(400,time),mDecelerateInterpolator);
  }
}
