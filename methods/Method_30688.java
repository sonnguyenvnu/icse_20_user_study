public static void setObject(@NonNull Field field,@Nullable Object object,Object value) throws ReflectedException {
  try {
    field.set(object,value);
  }
 catch (  IllegalAccessException e) {
    throw new ReflectedException(e);
  }
}
