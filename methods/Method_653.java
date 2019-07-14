public boolean containsReference(Object value){
  if (references == null) {
    return false;
  }
  SerialContext refContext=references.get(value);
  if (refContext == null) {
    return false;
  }
  if (value == Collections.emptyMap()) {
    return false;
  }
  Object fieldName=refContext.fieldName;
  return fieldName == null || fieldName instanceof Integer || fieldName instanceof String;
}
