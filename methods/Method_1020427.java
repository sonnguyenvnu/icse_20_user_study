/** 
 * Creates a new  {@link IntervalMetricReader}.
 * @param metricExporter the {@link MetricExporter} to be called after.
 * @param metricReader the {@link MetricReader} to be used to read metrics.
 * @param options the {@link Options} for the new {@link IntervalMetricReader}.
 * @return a new {@link IntervalMetricReader}.
 * @since 0.19
 */
public static IntervalMetricReader create(MetricExporter metricExporter,MetricReader metricReader,Options options){
  checkNotNull(options,"options");
  Duration exportInterval=checkNotNull(options.getExportInterval(),"exportInterval");
  checkArgument(exportInterval.compareTo(ZERO) > 0,"Export interval must be positive");
  return new IntervalMetricReader(new Worker(checkNotNull(metricExporter,"metricExporter"),exportInterval.toMillis(),checkNotNull(metricReader,"metricReader")));
}
