/** 
 * @deprecated Not for public use.  Using the delay param prevents streams from being efficiently shared.Please use  {@link HystrixConfigurationStream#observe()}and you can map to JSON string via  {@link HystrixConfigurationJsonStream#convertToString(HystrixConfiguration)}
 * @param delay interval between data emissions
 * @return sampled utilization as JSON string, taken on a timer
 */
@Deprecated public Observable<String> observeJson(int delay){
  return streamGenerator.call(delay).map(convertToJson);
}
