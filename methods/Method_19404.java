@Nullable @Override public int[] calculateDistanceToFinalSnap(@NonNull LayoutManager layoutManager,@NonNull View targetView){
  int[] out=new int[2];
  if (layoutManager.canScrollHorizontally()) {
    out[0]=distanceToStart(layoutManager,targetView,getHorizontalHelper(layoutManager));
  }
 else {
    out[0]=0;
  }
  if (layoutManager.canScrollVertically()) {
    out[1]=distanceToStart(layoutManager,targetView,getVerticalHelper(layoutManager));
  }
 else {
    out[1]=0;
  }
  return out;
}
