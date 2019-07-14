public static void tryClose(final AutoCloseable c){
  if (c == null) {
    return;
  }
  try {
    c.close();
  }
 catch (  Throwable ignored) {
  }
}
