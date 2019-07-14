public WireMockConfiguration withRootDirectory(String path){
  this.filesRoot=new SingleRootFileSource(path);
  return this;
}
