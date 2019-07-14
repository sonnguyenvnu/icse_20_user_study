static PropertyDescriptorBuilderConversionWrapper.MultiValue.Numeric<Long,LongMultiPBuilder> extractor(){
  return new PropertyDescriptorBuilderConversionWrapper.MultiValue.Numeric<Long,LongMultiPBuilder>(Long.class,ValueParserConstants.LONG_PARSER){
    @Override protected LongMultiPBuilder newBuilder(    String name){
      return new LongMultiPBuilder(name);
    }
  }
;
}
