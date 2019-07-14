@Nullable public static Class<?> getClass(final @NonNull String className){
  try {
    return Class.forName(className);
  }
 catch (  ClassNotFoundException e) {
    return null;
  }
}
