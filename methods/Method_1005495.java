public static TypeMappingOption wildcard(final boolean value){
  return new TypeMappingOption(){
    public void apply(    DozerBuilder.MappingBuilder fieldMappingBuilder){
      fieldMappingBuilder.wildcard(value);
    }
  }
;
}
