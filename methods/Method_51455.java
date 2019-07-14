public static PropertyDescriptorBuilderConversionWrapper.MultiValue.Packaged<Class,TypeMultiPBuilder> extractor(){
  return new PropertyDescriptorBuilderConversionWrapper.MultiValue.Packaged<Class,TypeMultiPBuilder>(Class.class,ValueParserConstants.CLASS_PARSER){
    @Override protected TypeMultiPBuilder newBuilder(    String name){
      return new TypeMultiPBuilder(name);
    }
  }
;
}
