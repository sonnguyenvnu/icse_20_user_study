/** 
 * Converts the given Metric into datapoints that can be sent to SignalFx.
 * @param metric The {@link Metric} containing the timeseries of each combination of label values.
 * @return A list of datapoints for the corresponding metric timeseries of this metric.
 */
static List<DataPoint> adapt(Metric metric){
  MetricDescriptor metricDescriptor=metric.getMetricDescriptor();
  MetricType metricType=getType(metricDescriptor.getType());
  if (metricType == null) {
    return Collections.emptyList();
  }
  DataPoint.Builder shared=DataPoint.newBuilder();
  shared.setMetric(metricDescriptor.getName());
  shared.setMetricType(metricType);
  ArrayList<DataPoint> datapoints=Lists.newArrayList();
  for (  TimeSeries timeSeries : metric.getTimeSeriesList()) {
    DataPoint.Builder builder=shared.clone();
    builder.addAllDimensions(createDimensions(metricDescriptor.getLabelKeys(),timeSeries.getLabelValues()));
    List<Point> points=timeSeries.getPoints();
    datapoints.ensureCapacity(datapoints.size() + points.size());
    for (    Point point : points) {
      datapoints.add(builder.setValue(createDatum(point.getValue())).build());
    }
  }
  return datapoints;
}
