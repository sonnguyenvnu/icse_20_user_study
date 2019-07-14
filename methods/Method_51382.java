static PropertyDescriptorBuilderConversionWrapper.MultiValue.Numeric<Double,DoubleMultiPBuilder> extractor(){
  return new PropertyDescriptorBuilderConversionWrapper.MultiValue.Numeric<Double,DoubleMultiPBuilder>(Double.class,ValueParserConstants.DOUBLE_PARSER){
    @Override protected DoubleMultiPBuilder newBuilder(    String name){
      return new DoubleMultiPBuilder(name);
    }
  }
;
}
