private Class<?>[] types(TypedValue[] typedValues){
  return Arrays.stream(typedValues).map(TypedValue::getClazz).toArray(Class[]::new);
}
