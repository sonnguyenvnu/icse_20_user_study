private static String getNativeConsoleEncoding(){
  try {
    Method m=Console.class.getDeclaredMethod("encoding");
    m.setAccessible(true);
    Object res=m.invoke(null);
    if (res instanceof String) {
      return (String)res;
    }
  }
 catch (  ReflectiveOperationException ignored) {
  }
  return null;
}
