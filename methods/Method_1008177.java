public RolloverRequestBuilder mapping(String type,String source){
  this.request.getCreateIndexRequest().mapping(type,source);
  return this;
}
