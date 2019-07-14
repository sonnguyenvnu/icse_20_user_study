/** 
 * Returns whether  {@code mediaPeriod} is the current loading media period. 
 */
public boolean isLoading(MediaPeriod mediaPeriod){
  return loading != null && loading.mediaPeriod == mediaPeriod;
}
