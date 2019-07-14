/** 
 * Sets the target time in microseconds within the stream to seek to.
 * @param timeUs The target time in microseconds within the stream.
 */
public final void setSeekTargetUs(long timeUs){
  if (seekOperationParams != null && seekOperationParams.getSeekTimeUs() == timeUs) {
    return;
  }
  seekOperationParams=createSeekParamsForTargetTimeUs(timeUs);
}
