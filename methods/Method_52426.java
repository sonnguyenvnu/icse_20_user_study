private String qualifyTypeName(String typeImage){
  if (typeImage == null) {
    return null;
  }
  final SourceFileScope fileScope=getEnclosingScope(SourceFileScope.class);
  String qualified=findQualifiedName(typeImage,fileScope.getQualifiedTypeNames().keySet());
  if (qualified != null) {
    return qualified;
  }
  qualified=findQualifiedName(typeImage,fileScope.getExplicitImports());
  if (qualified != null) {
    return qualified;
  }
  int dotIndex=typeImage.indexOf('.');
  if (dotIndex != -1) {
    qualified=findQualifiedName(typeImage.substring(0,dotIndex),fileScope.getExplicitImports());
    if (qualified != null) {
      return qualified.concat(typeImage.substring(dotIndex));
    }
  }
  return typeImage;
}
