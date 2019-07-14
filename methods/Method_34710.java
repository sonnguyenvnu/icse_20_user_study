/** 
 * {@link HystrixRequestLog} for current request as defined by {@link HystrixRequestContext}. <p> NOTE: This uses the default  {@link HystrixConcurrencyStrategy} or global override. If an injected strategy is being used by commands you must instead use{@link #getCurrentRequest(HystrixConcurrencyStrategy)}.
 * @return {@link HystrixRequestLog}
 */
public static HystrixRequestLog getCurrentRequest(){
  return currentRequestLog.get(HystrixPlugins.getInstance().getConcurrencyStrategy());
}
