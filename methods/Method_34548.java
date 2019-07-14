/** 
 * @deprecated Not for public use.  Using the delay param prevents streams from being efficiently shared.Please use  {@link HystrixUtilizationStream#observe()}
 * @param delay interval between data emissions
 * @return sampled utilization as Java object, taken on a timer
 */
@Deprecated public Observable<HystrixUtilization> observe(int delay){
  return streamGenerator.call(delay);
}
