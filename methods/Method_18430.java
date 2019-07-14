void unmountAllItems(){
  assertMainThread();
  if (mLayoutOutputsIds == null) {
    return;
  }
  for (int i=mLayoutOutputsIds.length - 1; i >= 0; i--) {
    unmountItem(i,mHostsByMarker);
  }
  mPreviousLocalVisibleRect.setEmpty();
  mNeedsRemount=true;
}
