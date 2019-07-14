static PropertyDescriptorBuilderConversionWrapper.MultiValue<Boolean,BooleanMultiPBuilder> extractor(){
  return new PropertyDescriptorBuilderConversionWrapper.MultiValue<Boolean,BooleanMultiPBuilder>(Boolean.class,ValueParserConstants.BOOLEAN_PARSER){
    @Override protected BooleanMultiPBuilder newBuilder(    String name){
      return new BooleanMultiPBuilder(name);
    }
  }
;
}
