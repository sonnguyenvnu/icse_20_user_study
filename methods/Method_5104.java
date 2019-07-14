@Override public int getLastWindowIndex(boolean shuffleModeEnabled){
  if (childCount == 0) {
    return C.INDEX_UNSET;
  }
  if (isAtomic) {
    shuffleModeEnabled=false;
  }
  int lastChildIndex=shuffleModeEnabled ? shuffleOrder.getLastIndex() : childCount - 1;
  while (getTimelineByChildIndex(lastChildIndex).isEmpty()) {
    lastChildIndex=getPreviousChildIndex(lastChildIndex,shuffleModeEnabled);
    if (lastChildIndex == C.INDEX_UNSET) {
      return C.INDEX_UNSET;
    }
  }
  return getFirstWindowIndexByChildIndex(lastChildIndex) + getTimelineByChildIndex(lastChildIndex).getLastWindowIndex(shuffleModeEnabled);
}
