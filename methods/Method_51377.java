static PropertyDescriptorBuilderConversionWrapper.SingleValue<Character,CharacterPBuilder> extractor(){
  return new PropertyDescriptorBuilderConversionWrapper.SingleValue<Character,CharacterPBuilder>(Character.class,ValueParserConstants.CHARACTER_PARSER){
    @Override protected CharacterPBuilder newBuilder(    String name){
      return new CharacterPBuilder(name);
    }
  }
;
}
