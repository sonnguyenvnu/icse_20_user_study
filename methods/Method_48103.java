public Map<String,Object> toMap() throws IOException {
  return mapReader.readValue(toGeoJson());
}
