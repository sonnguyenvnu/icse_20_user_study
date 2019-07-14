/** 
 * Finds the first fully visible child to be used as an anchor child if span count changes when state is restored. If no children is fully visible, returns a partially visible child instead of returning null.
 */
int findFirstVisibleItemPositionInt(){
  final View first=mShouldReverseLayout ? findFirstVisibleItemClosestToEnd(true) : findFirstVisibleItemClosestToStart(true);
  return first == null ? RecyclerView.NO_POSITION : getPosition(first);
}
