private ObjectFormatter initFormatterForType(Class<?> fieldClazz,String[] params){
  if (fieldClazz.equals(String.class) || List.class.isAssignableFrom(fieldClazz)) {
    return null;
  }
  Class<? extends ObjectFormatter> formatterClass=ObjectFormatters.get(BasicTypeFormatter.detectBasicClass(fieldClazz));
  if (formatterClass == null) {
    throw new IllegalStateException("Can't find formatter for field " + field.getName() + " of type " + fieldClazz);
  }
  return initFormatter(formatterClass,params);
}
