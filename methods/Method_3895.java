/** 
 * Estimates a position to which SnapHelper will try to scroll to in response to a fling.
 * @param layoutManager The {@link RecyclerView.LayoutManager} associated with the attached{@link RecyclerView}.
 * @param helper        The {@link OrientationHelper} that is created from the LayoutManager.
 * @param velocityX     The velocity on the x axis.
 * @param velocityY     The velocity on the y axis.
 * @return The diff between the target scroll position and the current position.
 */
private int estimateNextPositionDiffForFling(RecyclerView.LayoutManager layoutManager,OrientationHelper helper,int velocityX,int velocityY){
  int[] distances=calculateScrollDistance(velocityX,velocityY);
  float distancePerChild=computeDistancePerChild(layoutManager,helper);
  if (distancePerChild <= 0) {
    return 0;
  }
  int distance=Math.abs(distances[0]) > Math.abs(distances[1]) ? distances[0] : distances[1];
  return (int)Math.round(distance / distancePerChild);
}
