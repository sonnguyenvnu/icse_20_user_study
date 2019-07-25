static String canonicalise(String path){
  return path.startsWith("/") ? path.substring(1) : path;
}
