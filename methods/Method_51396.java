static PropertyDescriptorBuilderConversionWrapper.MultiValue.Numeric<Float,FloatMultiPBuilder> extractor(){
  return new PropertyDescriptorBuilderConversionWrapper.MultiValue.Numeric<Float,FloatMultiPBuilder>(Float.class,ValueParserConstants.FLOAT_PARSER){
    @Override protected FloatMultiPBuilder newBuilder(    String name){
      return new FloatMultiPBuilder(name);
    }
  }
;
}
