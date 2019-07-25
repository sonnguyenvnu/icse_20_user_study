private XContentBuilder values(short[] values) throws IOException {
  if (values == null) {
    return nullValue();
  }
  startArray();
  for (  short s : values) {
    value(s);
  }
  endArray();
  return this;
}
