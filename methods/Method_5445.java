@Override public void releasePeriod(MediaPeriod mediaPeriod){
  MergingMediaPeriod mergingPeriod=(MergingMediaPeriod)mediaPeriod;
  for (int i=0; i < mediaSources.length; i++) {
    mediaSources[i].releasePeriod(mergingPeriod.periods[i]);
  }
}
