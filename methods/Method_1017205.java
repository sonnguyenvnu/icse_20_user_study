public ByteString encode(SeriesKey key,Transform<Series,String> transform) throws Exception {
  return ByteString.copyFrom(category + "/" + key.getDate().toString() + "/" + transform.transform(key.getSeries()),Charsets.UTF_8);
}
