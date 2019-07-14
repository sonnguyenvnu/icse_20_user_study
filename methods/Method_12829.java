private Field field0(String name) throws ReflectException {
  Class<?> t=type();
  try {
    return accessible(t.getField(name));
  }
 catch (  NoSuchFieldException e) {
    do {
      try {
        return accessible(t.getDeclaredField(name));
      }
 catch (      NoSuchFieldException ignore) {
      }
      t=t.getSuperclass();
    }
 while (t != null);
    throw new ReflectException(e);
  }
}
