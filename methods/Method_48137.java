private static String getAbsolutePath(String file){
  return getAbsolutePath(new File(System.getProperty("user.dir")),file);
}
