private static void assertApiLevel17OrHigher(){
  if (Util.SDK_INT < 17) {
    throw new UnsupportedOperationException("Unsupported prior to API level 17");
  }
}
