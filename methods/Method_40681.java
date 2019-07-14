@NotNull private static String dirname(@NotNull String path){
  File f=new File(path);
  if (f.getParent() != null) {
    return f.getParent();
  }
 else {
    return path;
  }
}
