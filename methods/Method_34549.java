/** 
 * @deprecated Not for public use.  Using the delay param prevents streams from being efficiently shared.Please use  {@link HystrixUtilizationStream#observe()}and the  {@link #convertToJson(HystrixUtilization)} method
 * @param delay interval between data emissions
 * @return sampled utilization as JSON string, taken on a timer
 */
public Observable<String> observeJson(int delay){
  return streamGenerator.call(delay).map(convertToJsonFunc);
}
