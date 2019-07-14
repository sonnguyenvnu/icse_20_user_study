/** 
 * Resolves raw type name using the generics information from the class or method information.
 */
private String resolveRawTypeName(String typeName){
  if (typeName == null) {
    return null;
  }
  boolean isArray=typeName.startsWith(StringPool.LEFT_SQ_BRACKET);
  if (isArray) {
    typeName=typeName.substring(1);
  }
  String rawTypeName;
  if (generics.containsKey(typeName)) {
    rawTypeName=generics.get(typeName);
  }
 else {
    rawTypeName=declaredTypeGeneric.getOrDefault(typeName,typeName);
  }
  if (isArray) {
    rawTypeName='[' + rawTypeName;
  }
  return rawTypeName;
}
