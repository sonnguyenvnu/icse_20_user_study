private void setupPreviousMountableOutputData(LayoutState layoutState,Rect localVisibleRect){
  if (localVisibleRect.isEmpty()) {
    return;
  }
  final ArrayList<LayoutOutput> layoutOutputTops=layoutState.getMountableOutputTops();
  final ArrayList<LayoutOutput> layoutOutputBottoms=layoutState.getMountableOutputBottoms();
  final int mountableOutputCount=layoutState.getMountableOutputCount();
  mPreviousTopsIndex=layoutState.getMountableOutputCount();
  for (int i=0; i < mountableOutputCount; i++) {
    if (localVisibleRect.bottom <= layoutOutputTops.get(i).getBounds().top) {
      mPreviousTopsIndex=i;
      break;
    }
  }
  mPreviousBottomsIndex=layoutState.getMountableOutputCount();
  for (int i=0; i < mountableOutputCount; i++) {
    if (localVisibleRect.top < layoutOutputBottoms.get(i).getBounds().bottom) {
      mPreviousBottomsIndex=i;
      break;
    }
  }
}
