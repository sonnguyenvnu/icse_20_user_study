public static void error(String msg){
  if (quietMode) {
    return;
  }
  System.err.println(ERR_PREFIX + msg);
}
