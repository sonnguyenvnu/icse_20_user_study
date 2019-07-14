static PropertyDescriptorBuilderConversionWrapper.MultiValue<Character,CharacterMultiPBuilder> extractor(){
  return new PropertyDescriptorBuilderConversionWrapper.MultiValue<Character,CharacterMultiPBuilder>(Character.class,ValueParserConstants.CHARACTER_PARSER){
    @Override protected CharacterMultiPBuilder newBuilder(    String name){
      return new CharacterMultiPBuilder(name);
    }
  }
;
}
