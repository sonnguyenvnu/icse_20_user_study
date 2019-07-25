@Override public void invoke(MetricEvent metricEvent,Context context) throws Exception {
  if (StringUtils.isNullOrWhitespaceOnly(metricEvent.getName())) {
    throw new RuntimeException("No measurement defined");
  }
  Point.Builder builder=Point.measurement(metricEvent.getName()).time(metricEvent.getTimestamp(),TimeUnit.MILLISECONDS);
  if (!CollectionUtil.isNullOrEmpty(metricEvent.getFields())) {
    builder.fields(metricEvent.getFields());
  }
  if (!CollectionUtil.isNullOrEmpty(metricEvent.getTags())) {
    builder.tag(metricEvent.getTags());
  }
  Point point=builder.build();
  influxDBClient.write(point);
}
