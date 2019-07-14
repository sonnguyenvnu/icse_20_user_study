/** 
 * Returns the ratio of cache loading attempts which threw exceptions. This is defined as {@code loadFailureCount / (loadSuccessCount + loadFailureCount)}, or  {@code 0.0} when{@code loadSuccessCount + loadFailureCount == 0}. <p> <b>Note:</b> the values of the metrics are undefined in case of overflow (though it is guaranteed not to throw an exception). If you require specific handling, we recommend implementing your own stats collector.
 * @return the ratio of cache loading attempts which threw exceptions
 */
@NonNegative public double loadFailureRate(){
  long totalLoadCount=saturatedAdd(loadSuccessCount,loadFailureCount);
  return (totalLoadCount == 0) ? 0.0 : (double)loadFailureCount / totalLoadCount;
}
