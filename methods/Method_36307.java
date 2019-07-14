public void init(){
  if (isInitiated.compareAndSet(false,true)) {
    Assert.notNull(applicationContext,() -> "Application must not be null");
    Map<String,HealthIndicator> beansOfType=applicationContext.getBeansOfType(HealthIndicator.class);
    if (ClassUtils.isPresent(REACTOR_CLASS,null)) {
      applicationContext.getBeansOfType(ReactiveHealthIndicator.class).forEach((name,indicator) -> beansOfType.put(name,() -> indicator.health().block()));
    }
    healthIndicators=HealthCheckUtils.sortMapAccordingToValue(beansOfType,applicationContext.getAutowireCapableBeanFactory());
    StringBuilder healthIndicatorInfo=new StringBuilder(512).append("Found ").append(healthIndicators.size()).append(" HealthIndicator implementation:").append(String.join(",",healthIndicators.keySet()));
    logger.info(healthIndicatorInfo.toString());
  }
}
