public String version() throws IOException {
  Map m=(Map)retrieveAndParse("version");
  return (String)m.get("Version");
}
