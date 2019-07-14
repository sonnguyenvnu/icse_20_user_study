public boolean doHealthCheck(String beanId,HealthIndicator healthIndicator,Map<String,Health> healthMap){
  Assert.notNull(healthMap,() -> "HealthMap must not be null");
  boolean result;
  try {
    Health health=healthIndicator.health();
    Status status=health.getStatus();
    result=status.equals(Status.UP);
    if (result) {
      logger.info("HealthIndicator[{}] readiness check success.",beanId);
    }
 else {
      logger.error("HealthIndicator[{}] readiness check fail; the status is: {}; the detail is: {}.",beanId,status,objectMapper.writeValueAsString(health.getDetails()));
    }
    healthMap.put(getKey(beanId),health);
  }
 catch (  Exception e) {
    result=false;
    logger.error(String.format("Error occurred while doing HealthIndicator[%s] readiness check.",healthIndicator.getClass()),e);
  }
  return result;
}
