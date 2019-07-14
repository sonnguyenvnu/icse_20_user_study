private String getNameOrAlias(Field field){
  String fieldName=field.getName();
  if (field.getAlias() != null && !field.getAlias().isEmpty()) {
    fieldName=field.getAlias();
  }
  return fieldName;
}
