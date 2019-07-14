public Class<?> getFieldType(){
  popType();
  if (fieldType == null) {
    throw new RuntimeException();
  }
  return fieldType;
}
