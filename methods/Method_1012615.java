public static void info(String sql){
  if (logger != null && isCategoryOk(Category.INFO)) {
    doLog(-1,Category.INFO,"",sql);
  }
}
