private static Method initGetSuppressed(){
  try {
    return Throwable.class.getMethod("getSuppressed");
  }
 catch (  Throwable e) {
    return null;
  }
}
