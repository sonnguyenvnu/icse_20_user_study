static HystrixDashboardStream getNonSingletonInstanceOnlyUsedInUnitTests(int delayInMs){
  return new HystrixDashboardStream(delayInMs);
}
