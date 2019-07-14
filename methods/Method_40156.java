@NotNull private static File checkFile(String path){
  File f=new File(path);
  if (!f.canRead()) {
    _.die("Path not found or not readable: " + path);
  }
  return f;
}
