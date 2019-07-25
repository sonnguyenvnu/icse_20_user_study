public static void setup(MeterRegistry registry,MeterIdPrefix idPrefix,Cache<?,?> cache,Ticker ticker){
  requireNonNull(cache,"cache");
  if (!cache.policy().isRecordingStats()) {
    return;
  }
  final CaffeineMetrics metrics=MicrometerUtil.register(registry,idPrefix,CaffeineMetrics.class,CaffeineMetrics::new);
  metrics.add(cache,ticker);
}
