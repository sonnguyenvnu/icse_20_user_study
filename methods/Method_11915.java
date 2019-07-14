private static boolean hasSuppressed(Throwable exception){
  if (getSuppressed == null) {
    return false;
  }
  try {
    Throwable[] suppressed=(Throwable[])getSuppressed.invoke(exception);
    return suppressed.length != 0;
  }
 catch (  Throwable e) {
    return false;
  }
}
