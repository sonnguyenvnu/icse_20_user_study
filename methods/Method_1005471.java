public DozerField convert(){
  DozerField field=prepareField(name,type == null ? "" : type.value());
  field.setDateFormat(dateFormat);
  field.setMapGetMethod(mapGetMethod);
  field.setMapSetMethod(mapSetMethod);
  field.setTheGetMethod(getMethod);
  field.setTheSetMethod(setMethod);
  if (StringUtils.isNotEmpty(key)) {
    field.setKey(key);
  }
  if (isAccessible != null) {
    field.setAccessible(isAccessible);
  }
  field.setCreateMethod(createMethod);
  return field;
}
