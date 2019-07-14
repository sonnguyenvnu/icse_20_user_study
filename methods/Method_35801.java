public WireMockConfiguration usingFilesUnderClasspath(String path){
  fileSource(new ClasspathFileSource(path));
  return this;
}
