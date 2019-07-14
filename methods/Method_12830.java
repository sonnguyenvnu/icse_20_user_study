/** 
 * Searches a method with the exact same signature as desired. <p> If a public method is found in the class hierarchy, this method is returned. Otherwise a private method with the exact same signature is returned. If no exact match could be found, we let the  {@code NoSuchMethodException} pass through.
 */
private Method exactMethod(String name,Class<?>[] types) throws NoSuchMethodException {
  Class<?> t=type();
  try {
    return t.getMethod(name,types);
  }
 catch (  NoSuchMethodException e) {
    do {
      try {
        return t.getDeclaredMethod(name,types);
      }
 catch (      NoSuchMethodException ignore) {
      }
      t=t.getSuperclass();
    }
 while (t != null);
    throw new NoSuchMethodException();
  }
}
