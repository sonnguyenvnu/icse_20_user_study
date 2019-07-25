private XContentBuilder values(double[] values) throws IOException {
  if (values == null) {
    return nullValue();
  }
  startArray();
  for (  double b : values) {
    value(b);
  }
  endArray();
  return this;
}
