/** 
 * See  {@link #DefaultLoadErrorHandlingPolicy()} and {@link #DefaultLoadErrorHandlingPolicy(int)}for documentation about the behavior of this method.
 */
@Override public int getMinimumLoadableRetryCount(int dataType){
  if (minimumLoadableRetryCount == DEFAULT_BEHAVIOR_MIN_LOADABLE_RETRY_COUNT) {
    return dataType == C.DATA_TYPE_MEDIA_PROGRESSIVE_LIVE ? DEFAULT_MIN_LOADABLE_RETRY_COUNT_PROGRESSIVE_LIVE : DEFAULT_MIN_LOADABLE_RETRY_COUNT;
  }
 else {
    return minimumLoadableRetryCount;
  }
}
