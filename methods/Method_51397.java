static PropertyDescriptorBuilderConversionWrapper.SingleValue.Numeric<Float,FloatPBuilder> extractor(){
  return new PropertyDescriptorBuilderConversionWrapper.SingleValue.Numeric<Float,FloatPBuilder>(Float.class,ValueParserConstants.FLOAT_PARSER){
    @Override protected FloatPBuilder newBuilder(    String name){
      return new FloatPBuilder(name);
    }
  }
;
}
