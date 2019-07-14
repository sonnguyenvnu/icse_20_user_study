static PropertyDescriptorBuilderConversionWrapper.SingleValue<String,StringPBuilder> extractor(){
  return new PropertyDescriptorBuilderConversionWrapper.SingleValue<String,StringPBuilder>(String.class,ValueParserConstants.STRING_PARSER){
    @Override protected StringPBuilder newBuilder(    String name){
      return new StringPBuilder(name);
    }
  }
;
}
