public RecordSpecBuilder matchRequestBodyWithEqualToXml(){
  this.requestBodyPatternFactory=new RequestBodyEqualToXmlPatternFactory();
  return this;
}
