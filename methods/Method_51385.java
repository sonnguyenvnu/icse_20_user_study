static PropertyDescriptorBuilderConversionWrapper.SingleValue.Numeric<Double,DoublePBuilder> extractor(){
  return new PropertyDescriptorBuilderConversionWrapper.SingleValue.Numeric<Double,DoublePBuilder>(Double.class,ValueParserConstants.DOUBLE_PARSER){
    @Override protected DoublePBuilder newBuilder(    String name){
      return new DoublePBuilder(name);
    }
  }
;
}
