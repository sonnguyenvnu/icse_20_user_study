/** 
 * Releases the given  {@code mediaPeriod}, logging and suppressing any errors. 
 */
private static void releaseMediaPeriod(MediaPeriodId id,MediaSource mediaSource,MediaPeriod mediaPeriod){
  try {
    if (id.endPositionUs != C.TIME_UNSET && id.endPositionUs != C.TIME_END_OF_SOURCE) {
      mediaSource.releasePeriod(((ClippingMediaPeriod)mediaPeriod).mediaPeriod);
    }
 else {
      mediaSource.releasePeriod(mediaPeriod);
    }
  }
 catch (  RuntimeException e) {
    Log.e(TAG,"Period release failed.",e);
  }
}
