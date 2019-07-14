public RecordSpecBuilder matchRequestBodyWithEqualTo(Boolean caseInsensitive){
  this.requestBodyPatternFactory=new RequestBodyEqualToPatternFactory(caseInsensitive);
  return this;
}
