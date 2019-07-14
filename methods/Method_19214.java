private int estimateNextPositionDiffForFling(RecyclerView.LayoutManager layoutManager,OrientationHelper helper,int velocityX,int velocityY){
  int[] distances=calculateScrollDistance(velocityX,velocityY);
  float distancePerChild=computeDistancePerChild(layoutManager,helper);
  if (distancePerChild <= 0) {
    return 0;
  }
  int distance=Math.abs(distances[0]) > Math.abs(distances[1]) ? distances[0] : distances[1];
  return (int)Math.round(distance / distancePerChild);
}
