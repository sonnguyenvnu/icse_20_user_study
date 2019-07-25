private XContentBuilder values(float[] values) throws IOException {
  if (values == null) {
    return nullValue();
  }
  startArray();
  for (  float f : values) {
    value(f);
  }
  endArray();
  return this;
}
