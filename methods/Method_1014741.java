public static void error(String msg,Throwable t){
  if (quietMode) {
    return;
  }
  System.err.println(ERR_PREFIX + msg);
  if (t != null) {
    t.printStackTrace();
  }
}
