/** 
 * Provided for readiness check.
 * @param healthMap
 * @return
 */
public boolean readinessHealthCheck(Map<String,Health> healthMap){
  Assert.notNull(healthIndicators,() -> "HealthIndicators must not be null.");
  logger.info("Begin SOFABoot HealthIndicator readiness check.");
  boolean result=healthIndicators.entrySet().stream().filter(entry -> !(entry.getValue() instanceof SofaBootHealthIndicator)).map(entry -> doHealthCheck(entry.getKey(),entry.getValue(),healthMap)).reduce(true,BinaryOperators.andBoolean());
  if (result) {
    logger.info("SOFABoot HealthIndicator readiness check result: success.");
  }
 else {
    logger.error("SOFABoot HealthIndicator readiness check result: failed.");
  }
  return result;
}
