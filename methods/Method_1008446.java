public XContentBuilder latlon(String name,double lat,double lon) throws IOException {
  return field(name).latlon(lat,lon);
}
