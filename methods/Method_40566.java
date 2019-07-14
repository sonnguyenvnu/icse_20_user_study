public static File joinPath(String dir,String file){
  if (dir.endsWith("/")) {
    return new File(dir + file);
  }
  return new File(dir + "/" + file);
}
