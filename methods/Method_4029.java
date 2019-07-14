/** 
 * Snaps to a target view which currently exists in the attached  {@link RecyclerView}. This method is used to snap the view when the  {@link RecyclerView} is first attached; whensnapping was triggered by a scroll and when the fling is at its final stages.
 */
void snapToTargetExistingView(){
  if (mRecyclerView == null) {
    return;
  }
  RecyclerView.LayoutManager layoutManager=mRecyclerView.getLayoutManager();
  if (layoutManager == null) {
    return;
  }
  View snapView=findSnapView(layoutManager);
  if (snapView == null) {
    return;
  }
  int[] snapDistance=calculateDistanceToFinalSnap(layoutManager,snapView);
  if (snapDistance[0] != 0 || snapDistance[1] != 0) {
    mRecyclerView.smoothScrollBy(snapDistance[0],snapDistance[1]);
  }
}
