@Override public MetricEvent deserialize(byte[] bytes) throws IOException {
  return gson.fromJson(new String(bytes),MetricEvent.class);
}
