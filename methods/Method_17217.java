/** 
 * Returns the average time spent loading new values. This is defined as {@code totalLoadTime / (loadSuccessCount + loadFailureCount)}. <p> <b>Note:</b> the values of the metrics are undefined in case of overflow (though it is guaranteed not to throw an exception). If you require specific handling, we recommend implementing your own stats collector.
 * @return the average time spent loading new values
 */
@NonNegative public double averageLoadPenalty(){
  long totalLoadCount=saturatedAdd(loadSuccessCount,loadFailureCount);
  return (totalLoadCount == 0) ? 0.0 : (double)totalLoadTime / totalLoadCount;
}
