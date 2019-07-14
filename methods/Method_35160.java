@Nullable @SuppressWarnings("unchecked") public static <T>Class<? extends T> classForName(@NonNull String className,boolean allowEmptyName){
  if (allowEmptyName && TextUtils.isEmpty(className)) {
    return null;
  }
  try {
    return (Class<? extends T>)Class.forName(className);
  }
 catch (  Exception e) {
    throw new RuntimeException("An exception occurred while finding class for name " + className + ". " + e.getMessage());
  }
}
