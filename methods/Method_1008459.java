public static Throwable unwrap(Throwable t,Class<?>... clazzes){
  if (t != null) {
    do {
      for (      Class<?> clazz : clazzes) {
        if (clazz.isInstance(t)) {
          return t;
        }
      }
    }
 while ((t=t.getCause()) != null);
  }
  return null;
}
