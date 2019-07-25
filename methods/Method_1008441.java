private XContentBuilder values(long[] values) throws IOException {
  if (values == null) {
    return nullValue();
  }
  startArray();
  for (  long l : values) {
    value(l);
  }
  endArray();
  return this;
}
