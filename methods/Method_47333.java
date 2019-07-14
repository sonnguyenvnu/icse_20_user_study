public static boolean canListFiles(File f){
  return f.canRead() && f.isDirectory();
}
