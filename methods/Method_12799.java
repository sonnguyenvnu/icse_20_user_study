protected Field findField(@NonNull String name) throws NoSuchFieldException {
  try {
    return mType.getField(name);
  }
 catch (  NoSuchFieldException e) {
    for (Class<?> cls=mType; cls != null; cls=cls.getSuperclass()) {
      try {
        return cls.getDeclaredField(name);
      }
 catch (      NoSuchFieldException ex) {
      }
    }
    throw e;
  }
}
