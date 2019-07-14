/** 
 * Retrieve the batch size for the  {@link HystrixCollapser} being invoked at a given percentile.<p> Percentile capture and calculation is configured via  {@link HystrixCollapserProperties#metricsRollingStatisticalWindowInMilliseconds()} and other related properties.
 * @param percentile Percentile such as 50, 99, or 99.5.
 * @return batch size
 */
public int getBatchSizePercentile(double percentile){
  return rollingCollapserBatchSizeDistributionStream.getLatestPercentile(percentile);
}
