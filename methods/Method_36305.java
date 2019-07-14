public boolean afterReadinessCheckCallback(Map<String,Health> healthMap){
  logger.info("Begin ReadinessCheckCallback readiness check");
  Assert.notNull(readinessCheckCallbacks,"ReadinessCheckCallbacks must not be null.");
  boolean result=readinessCheckCallbacks.entrySet().stream().map(entry -> doHealthCheckCallback(entry.getKey(),entry.getValue(),healthMap)).reduce(true,BinaryOperators.andBoolean());
  if (result) {
    logger.info("ReadinessCheckCallback readiness check result: success.");
  }
 else {
    logger.error("ReadinessCheckCallback readiness check result: failed.");
  }
  return result;
}
