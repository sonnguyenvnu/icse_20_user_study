public RecordSpecBuilder matchRequestBodyWithEqualToJson(Boolean ignoreArrayOrder,Boolean ignoreExtraElements){
  this.requestBodyPatternFactory=new RequestBodyEqualToJsonPatternFactory(ignoreArrayOrder,ignoreExtraElements);
  return this;
}
