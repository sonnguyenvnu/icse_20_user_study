static PropertyDescriptorBuilderConversionWrapper.SingleValue.Packaged<Method,MethodPBuilder> extractor(){
  return new PropertyDescriptorBuilderConversionWrapper.SingleValue.Packaged<Method,MethodPBuilder>(Method.class,ValueParserConstants.METHOD_PARSER){
    @Override protected MethodPBuilder newBuilder(    String name){
      return new MethodPBuilder(name);
    }
  }
;
}
