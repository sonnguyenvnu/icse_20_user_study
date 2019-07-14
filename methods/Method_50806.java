private static String getConsoleEncoding(){
  Console console=System.console();
  if (console != null) {
    try {
      Field f=Console.class.getDeclaredField("cs");
      f.setAccessible(true);
      Object res=f.get(console);
      if (res instanceof Charset) {
        return ((Charset)res).name();
      }
    }
 catch (    ReflectiveOperationException ignored) {
    }
    return getNativeConsoleEncoding();
  }
  return null;
}
