public static void error(String sql){
  System.err.println("Warning: " + sql);
  if (logger != null) {
    doLog(-1,Category.ERROR,"",sql);
  }
}
