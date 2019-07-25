public void finish(double y){
  final int missingClose=getMissingClose();
  for (int i=0; i < missingClose; i++) {
    addSegmentVariation(LifeSegmentVariation.SMALLER,y,null);
  }
}
