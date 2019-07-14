@Override public void releasePeriod(MediaPeriod mediaPeriod){
  ((SingleSampleMediaPeriod)mediaPeriod).release();
}
