public void init(){
  if (isInitiated.compareAndSet(false,true)) {
    Assert.notNull(applicationContext,() -> "Application must not be null");
    Map<String,HealthChecker> beansOfType=applicationContext.getBeansOfType(HealthChecker.class);
    healthCheckers=HealthCheckUtils.sortMapAccordingToValue(beansOfType,applicationContext.getAutowireCapableBeanFactory());
    StringBuilder healthCheckInfo=new StringBuilder(512).append("Found ").append(healthCheckers.size()).append(" HealthChecker implementation:").append(String.join(",",healthCheckers.keySet()));
    logger.info(healthCheckInfo.toString());
  }
}
