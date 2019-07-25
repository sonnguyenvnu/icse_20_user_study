private XContentBuilder values(int[] values) throws IOException {
  if (values == null) {
    return nullValue();
  }
  startArray();
  for (  int i : values) {
    value(i);
  }
  endArray();
  return this;
}
