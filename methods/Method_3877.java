@Override protected void onTargetFound(View targetView,RecyclerView.State state,Action action){
  final int dx=calculateDxToMakeVisible(targetView);
  final int time=calculateTimeForDeceleration(dx);
  if (time > 0) {
    action.update(-dx,0,Math.max(400,time),mDecelerateInterpolator);
  }
}
