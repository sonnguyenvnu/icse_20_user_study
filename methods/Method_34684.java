/** 
 * The mean (average) execution time (in milliseconds) for the  {@link HystrixCommand#run()}. <p> This uses the same backing data as  {@link #getExecutionTimePercentile};
 * @return int time in milliseconds
 */
public int getExecutionTimeMean(){
  return rollingCommandLatencyDistributionStream.getLatestMean();
}
