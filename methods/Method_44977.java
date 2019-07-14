private FieldDefinition findFieldInType(TypeDefinition typeDef,String uniqueStr){
  String[] linkParts=uniqueStr.split("\\|");
  if (linkParts.length != 4)   return null;
  String fieldName=linkParts[3];
  if (fieldName.trim().length() <= 0)   return null;
  List<FieldDefinition> declaredFields=typeDef.getDeclaredFields();
  if (declaredFields == null)   return null;
  boolean isFound=false;
  for (  FieldDefinition declaredField : declaredFields) {
    isFound=(declaredField != null && fieldName.equals(declaredField.getName()));
    if (isFound) {
      if (declaredField.isSynthetic()) {
        return null;
      }
 else {
        return declaredField;
      }
    }
  }
  return null;
}
