@Override public byte[] serialize(MetricEvent metricEvent){
  return gson.toJson(metricEvent).getBytes(Charset.forName("UTF-8"));
}
