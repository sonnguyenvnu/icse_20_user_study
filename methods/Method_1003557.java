public Set<DeclaredType> uses(){
  Set<DeclaredType> uses=new LinkedHashSet<>();
  for (  TypeMirror usedMapperType : mapperPrism.uses()) {
    uses.add((DeclaredType)usedMapperType);
  }
  if (mapperConfigPrism != null) {
    for (    TypeMirror usedMapperType : mapperConfigPrism.uses()) {
      uses.add((DeclaredType)usedMapperType);
    }
  }
  return uses;
}
