protected void safelyCreateCumulativeCountForEvent(final String name,final Func0<HystrixRollingNumberEvent> eventThunk){
  metricRegistry.register(createMetricName(name),new Gauge<Long>(){
    @Override public Long getValue(){
      try {
        return metrics.getCumulativeCount(eventThunk.call());
      }
 catch (      NoSuchFieldError error) {
        logger.error("While publishing CodaHale metrics, error looking up eventType for : {}.  Please check that all Hystrix versions are the same!",name);
        return 0L;
      }
    }
  }
);
}
