public static void warn(String msg){
  if (quietMode) {
    return;
  }
  System.err.println(WARN_PREFIX + msg);
}
