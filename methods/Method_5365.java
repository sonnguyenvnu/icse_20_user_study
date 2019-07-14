@Override public void releasePeriod(MediaPeriod mediaPeriod){
  ((HlsMediaPeriod)mediaPeriod).release();
}
