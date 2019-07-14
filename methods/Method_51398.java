static PropertyDescriptorBuilderConversionWrapper.MultiValue.Numeric<Integer,IntegerMultiPBuilder> extractor(){
  return new PropertyDescriptorBuilderConversionWrapper.MultiValue.Numeric<Integer,IntegerMultiPBuilder>(Integer.class,ValueParserConstants.INTEGER_PARSER){
    @Override protected IntegerMultiPBuilder newBuilder(    String name){
      return new IntegerMultiPBuilder(name);
    }
  }
;
}
