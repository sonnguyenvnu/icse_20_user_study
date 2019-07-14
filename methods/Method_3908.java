@Nullable @Override public int[] calculateDistanceToFinalSnap(@NonNull RecyclerView.LayoutManager layoutManager,@NonNull View targetView){
  int[] out=new int[2];
  if (layoutManager.canScrollHorizontally()) {
    out[0]=distanceToCenter(layoutManager,targetView,getHorizontalHelper(layoutManager));
  }
 else {
    out[0]=0;
  }
  if (layoutManager.canScrollVertically()) {
    out[1]=distanceToCenter(layoutManager,targetView,getVerticalHelper(layoutManager));
  }
 else {
    out[1]=0;
  }
  return out;
}
