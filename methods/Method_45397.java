private ImmutableList<Field> getFieldsForCurrent(final Class<?> clazz){
  Field[] fields=clazz.getDeclaredFields();
  for (  Field field : fields) {
    field.setAccessible(true);
  }
  return copyOf(fields);
}
