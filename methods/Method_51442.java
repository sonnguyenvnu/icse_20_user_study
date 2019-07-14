static SingleValue<Pattern,RegexPBuilder> extractor(){
  return new PropertyDescriptorBuilderConversionWrapper.SingleValue<Pattern,RegexPBuilder>(Pattern.class,ValueParserConstants.REGEX_PARSER){
    @Override protected RegexPBuilder newBuilder(    String name){
      return new RegexPBuilder(name);
    }
  }
;
}
