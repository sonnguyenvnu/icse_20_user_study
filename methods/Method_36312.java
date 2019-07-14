/** 
 * Do readiness health check.
 */
public void readinessHealthCheck(){
  if (skipAllCheck()) {
    logger.warn("Skip all readiness health check.");
  }
 else {
    if (skipComponent()) {
      logger.warn("Skip HealthChecker health check.");
    }
 else {
      healthCheckerStatus=healthCheckerProcessor.readinessHealthCheck(healthCheckerDetails);
    }
    if (skipIndicator()) {
      logger.warn("Skip HealthIndicator health check.");
    }
 else {
      healthIndicatorStatus=healthIndicatorProcessor.readinessHealthCheck(healthIndicatorDetails);
    }
  }
  healthCallbackStatus=afterReadinessCheckCallbackProcessor.afterReadinessCheckCallback(healthCallbackDetails);
  if (healthCheckerStatus && healthIndicatorStatus && healthCallbackStatus) {
    logger.info("Readiness check result: success");
  }
 else {
    logger.error("Readiness check result: fail");
  }
}
