protected Method findMethod(@NonNull String name,@Nullable Class<?>... parameterTypes) throws NoSuchMethodException {
  try {
    return mType.getMethod(name,parameterTypes);
  }
 catch (  NoSuchMethodException e) {
    for (Class<?> cls=mType; cls != null; cls=cls.getSuperclass()) {
      try {
        return cls.getDeclaredMethod(name,parameterTypes);
      }
 catch (      NoSuchMethodException ex) {
      }
    }
    throw e;
  }
}
