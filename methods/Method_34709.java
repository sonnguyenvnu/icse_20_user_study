/** 
 * {@link HystrixRequestLog} for current request as defined by {@link HystrixRequestContext}.
 * @return {@link HystrixRequestLog}
 */
public static HystrixRequestLog getCurrentRequest(HystrixConcurrencyStrategy concurrencyStrategy){
  return currentRequestLog.get(concurrencyStrategy);
}
