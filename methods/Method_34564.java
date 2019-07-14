private static synchronized HystrixServoMetricsPublisher createInstance(){
  if (INSTANCE == null) {
    return new HystrixServoMetricsPublisher();
  }
 else {
    return INSTANCE;
  }
}
