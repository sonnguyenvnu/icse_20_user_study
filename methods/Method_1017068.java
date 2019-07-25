public static MetricReadResult create(final MetricCollection metrics,final SortedMap<String,String> resources){
  return new MetricReadResult(metrics,resources);
}
