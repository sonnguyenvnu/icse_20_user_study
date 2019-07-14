private String createUniqueStrForReference(Object reference){
  String uniqueStr=null;
  if (reference instanceof TypeReference) {
    TypeReference type=(TypeReference)reference;
    String pathAndTypeStr=getPathAndTypeStr(type);
    if (pathAndTypeStr != null) {
      uniqueStr="type|" + pathAndTypeStr;
    }
  }
 else   if (reference instanceof MethodReference) {
    MethodReference method=(MethodReference)reference;
    String pathAndTypeStr=getPathAndTypeStr(method.getDeclaringType());
    if (pathAndTypeStr != null) {
      uniqueStr="method|" + pathAndTypeStr + "|" + method.getName() + "|" + method.getErasedSignature();
    }
  }
 else   if (reference instanceof FieldReference) {
    FieldReference field=(FieldReference)reference;
    String pathAndTypeStr=getPathAndTypeStr(field.getDeclaringType());
    if (pathAndTypeStr != null) {
      uniqueStr="field|" + pathAndTypeStr + "|" + field.getName();
    }
  }
  return uniqueStr;
}
