protected void safelyCreateRollingGauge(final String name,final Func0<HystrixEventType> eventThunk){
  metricsRegistry.newGauge(createMetricName(name),new Gauge<Long>(){
    @Override public Long value(){
      try {
        HystrixRollingNumberEvent eventType=HystrixRollingNumberEvent.from(eventThunk.call());
        return metrics.getRollingCount(eventType);
      }
 catch (      NoSuchFieldError error) {
        logger.error("While publishing Yammer metrics, error looking up eventType for : {}.  Please check that all Hystrix versions are the same!",name);
        return 0L;
      }
    }
  }
);
}
