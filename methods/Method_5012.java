private void updateStopFlags(int flags,int values){
  Assertions.checkState(!released);
  int updatedStickyStopFlags=(values & flags) | (stickyStopFlags & ~flags);
  if (stickyStopFlags != updatedStickyStopFlags) {
    stickyStopFlags=updatedStickyStopFlags;
    for (int i=0; i < downloads.size(); i++) {
      downloads.get(i).updateStopFlags(flags,values);
    }
    logdFlags("Sticky stop flags are updated",updatedStickyStopFlags);
  }
}
