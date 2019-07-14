/** 
 * The mean (average) execution time (in milliseconds) for  {@link HystrixCommand#execute()} or {@link HystrixCommand#queue()}. <p> This uses the same backing data as  {@link #getTotalTimePercentile};
 * @return int time in milliseconds
 */
public int getTotalTimeMean(){
  return rollingCommandUserLatencyDistributionStream.getLatestMean();
}
