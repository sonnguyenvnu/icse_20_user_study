public XContentBuilder value(GeoPoint value) throws IOException {
  if (value == null) {
    return nullValue();
  }
  return latlon(value.getLat(),value.getLon());
}
