@NonNull public static Field getAccessibleField(@NonNull Class<?> ownerClass,@NonNull String fieldName) throws ReflectedException {
  try {
    Field field=ownerClass.getDeclaredField(fieldName);
    field.setAccessible(true);
    return field;
  }
 catch (  NoSuchFieldException e) {
    throw new ReflectedException(e);
  }
}
