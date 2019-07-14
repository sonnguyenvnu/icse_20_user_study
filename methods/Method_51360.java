static PropertyDescriptorBuilderConversionWrapper.SingleValue<Boolean,BooleanPBuilder> extractor(){
  return new PropertyDescriptorBuilderConversionWrapper.SingleValue<Boolean,BooleanPBuilder>(Boolean.class,ValueParserConstants.BOOLEAN_PARSER){
    @Override protected BooleanPBuilder newBuilder(    String name){
      return new BooleanPBuilder(name);
    }
  }
;
}
