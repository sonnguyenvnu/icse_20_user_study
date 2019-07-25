public List<TypeMirror> imports(){
  List<TypeMirror> imports=new ArrayList<>();
  imports.addAll(mapperPrism.imports());
  if (mapperConfigPrism != null) {
    imports.addAll(mapperConfigPrism.imports());
  }
  return imports;
}
