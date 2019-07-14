protected void safelyCreateRollingCountForEvent(final String name,final Func0<HystrixRollingNumberEvent> eventThunk){
  metricsRegistry.newGauge(createMetricName(name),new Gauge<Long>(){
    @Override public Long value(){
      try {
        return metrics.getRollingCount(eventThunk.call());
      }
 catch (      NoSuchFieldError error) {
        logger.error("While publishing Yammer metrics, error looking up eventType for : {}.  Please check that all Hystrix versions are the same!",name);
        return 0L;
      }
    }
  }
);
}
