private static HystrixRequestLog initRequestLog(boolean enabled,HystrixConcurrencyStrategy concurrencyStrategy){
  if (enabled) {
    return HystrixRequestLog.getCurrentRequest(concurrencyStrategy);
  }
 else {
    return null;
  }
}
