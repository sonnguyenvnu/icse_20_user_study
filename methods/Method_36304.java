public void init(){
  if (isInitiated.compareAndSet(false,true)) {
    Assert.notNull(applicationContext,() -> "Application must not be null");
    Map<String,ReadinessCheckCallback> beansOfType=applicationContext.getBeansOfType(ReadinessCheckCallback.class);
    readinessCheckCallbacks=HealthCheckUtils.sortMapAccordingToValue(beansOfType,applicationContext.getAutowireCapableBeanFactory());
    StringBuilder applicationCallbackInfo=new StringBuilder(512).append("Found ").append(readinessCheckCallbacks.size()).append(" ReadinessCheckCallback implementation: ").append(String.join(",",beansOfType.keySet()));
    logger.info(applicationCallbackInfo.toString());
  }
}
