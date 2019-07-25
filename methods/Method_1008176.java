public RolloverRequestBuilder settings(Settings settings){
  this.request.getCreateIndexRequest().settings(settings);
  return this;
}
