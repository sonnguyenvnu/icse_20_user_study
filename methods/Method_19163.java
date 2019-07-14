private int getSmoothScrollTarget(boolean forward,int defaultTarget){
switch (mSnapMode) {
case SNAP_NONE:
    return defaultTarget;
case SNAP_TO_START:
  return Math.max(0,forward ? mFirstCompletelyVisibleItemPosition + 1 : mFirstCompletelyVisibleItemPosition - 1);
case SNAP_TO_END:
return Math.max(0,forward ? mLastCompletelyVisibleItemPosition + 1 : mLastCompletelyVisibleItemPosition - 1);
case SNAP_TO_CENTER:
case SNAP_TO_CENTER_CHILD:
final RecyclerView recyclerView=getRecyclerView();
if (recyclerView == null) {
return defaultTarget;
}
final int centerPositionX=recyclerView.getWidth() / 2;
final int centerPositionY=recyclerView.getHeight() / 2;
final View centerView=recyclerView.findChildViewUnder(centerPositionX,centerPositionY);
if (centerView == null) {
return defaultTarget;
}
final int centerViewPosition=recyclerView.getChildAdapterPosition(centerView);
return Math.max(0,forward ? centerViewPosition + 1 : centerViewPosition - 1);
default :
return defaultTarget;
}
}
