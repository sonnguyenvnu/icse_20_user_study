public static String _XXXXX_(String path){
  if (path.contains("/")) {
    return path.substring(path.lastIndexOf("/") + 1);
  }
 else {
    return path;
  }
}