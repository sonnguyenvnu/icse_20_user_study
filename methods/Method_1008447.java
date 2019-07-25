private XContentBuilder values(Object[] values,boolean ensureNoSelfReferences) throws IOException {
  if (values == null) {
    return nullValue();
  }
  return value(Arrays.asList(values),ensureNoSelfReferences);
}
