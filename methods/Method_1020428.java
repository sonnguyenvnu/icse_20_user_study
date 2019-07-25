/** 
 * Creates a new  {@link MetricReader}.
 * @param options the options for {@link MetricReader}.
 * @return a new {@link MetricReader}.
 * @since 0.19
 */
public static MetricReader create(Options options){
  checkNotNull(options,"options");
  return new MetricReader(checkNotNull(options.getMetricProducerManager(),"metricProducerManager"),checkNotNull(options.getSpanName(),"spanName"));
}
