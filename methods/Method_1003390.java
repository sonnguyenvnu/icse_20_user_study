private static void mkdirs(File f){
  if (!f.exists()) {
    if (!f.mkdirs()) {
      throw new RuntimeException("Can not create directory " + f.getAbsolutePath());
    }
  }
}
