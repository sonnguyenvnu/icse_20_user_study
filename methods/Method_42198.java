private void triggerSelectionAllUpTo(int elemPos){
  int indexRightBeforeOrAfter=-1, minOffset=Integer.MAX_VALUE;
  for (  Integer selectedPosition : selectedPositions) {
    int offset=Math.abs(elemPos - selectedPosition);
    if (offset < minOffset) {
      minOffset=offset;
      indexRightBeforeOrAfter=selectedPosition;
    }
  }
  if (indexRightBeforeOrAfter != -1) {
    for (int index=Math.min(elemPos,indexRightBeforeOrAfter); index <= Math.max(elemPos,indexRightBeforeOrAfter); index++) {
      if (timelineItems.get(index) != null && timelineItems.get(index) instanceof Media) {
        selectedPositions.add(index);
        notifyItemChanged(index);
      }
    }
    actionsListener.onSelectionCountChanged(selectedPositions.size(),mediaItems.size());
  }
}
