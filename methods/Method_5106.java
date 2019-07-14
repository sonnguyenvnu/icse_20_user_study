private int getNextChildIndex(int childIndex,boolean shuffleModeEnabled){
  return shuffleModeEnabled ? shuffleOrder.getNextIndex(childIndex) : childIndex < childCount - 1 ? childIndex + 1 : C.INDEX_UNSET;
}
