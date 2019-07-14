public synchronized void updateMeasureListener(@Nullable MeasureListener measureListener){
  if (mComponentTree != null) {
    mComponentTree.updateMeasureListener(measureListener);
  }
}
