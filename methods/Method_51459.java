static PropertyDescriptorBuilderConversionWrapper.SingleValue.Packaged<Class,TypePBuilder> extractor(){
  return new PropertyDescriptorBuilderConversionWrapper.SingleValue.Packaged<Class,TypePBuilder>(Class.class,ValueParserConstants.CLASS_PARSER){
    @Override protected TypePBuilder newBuilder(    String name){
      return new TypePBuilder(name);
    }
  }
;
}
