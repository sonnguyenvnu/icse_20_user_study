@Override public void releasePeriod(MediaPeriod mediaPeriod){
  ((ExtractorMediaPeriod)mediaPeriod).release();
}
