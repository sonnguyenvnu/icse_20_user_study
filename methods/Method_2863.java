public static void setStaticFinalField(Field field,Object value) throws NoSuchFieldException, IllegalAccessException {
  field.setAccessible(true);
  Field modifiersField=Field.class.getDeclaredField(MODIFIERS_FIELD);
  modifiersField.setAccessible(true);
  int modifiers=modifiersField.getInt(field);
  modifiers&=~Modifier.FINAL;
  modifiersField.setInt(field,modifiers);
  FieldAccessor fa=reflection.newFieldAccessor(field,false);
  fa.set(null,value);
}
