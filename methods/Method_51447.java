static PropertyDescriptorBuilderConversionWrapper.MultiValue<String,StringMultiPBuilder> extractor(){
  return new PropertyDescriptorBuilderConversionWrapper.MultiValue<String,StringMultiPBuilder>(String.class,ValueParserConstants.STRING_PARSER){
    @Override protected StringMultiPBuilder newBuilder(    String name){
      return new StringMultiPBuilder(name);
    }
  }
;
}
