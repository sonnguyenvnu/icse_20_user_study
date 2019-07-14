void prepareLayoutStateForDelta(int delta,RecyclerView.State state){
  final int referenceChildPosition;
  final int layoutDir;
  if (delta > 0) {
    layoutDir=LayoutState.LAYOUT_END;
    referenceChildPosition=getLastChildPosition();
  }
 else {
    layoutDir=LayoutState.LAYOUT_START;
    referenceChildPosition=getFirstChildPosition();
  }
  mLayoutState.mRecycle=true;
  updateLayoutState(referenceChildPosition,state);
  setLayoutStateDirection(layoutDir);
  mLayoutState.mCurrentPosition=referenceChildPosition + mLayoutState.mItemDirection;
  mLayoutState.mAvailable=Math.abs(delta);
}
