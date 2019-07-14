private static String toJoinPath(final String path){
  if (Strings.isNullOrEmpty(path)) {
    return "";
  }
  if (path.startsWith(SEPARATOR)) {
    return path.substring(1);
  }
  return path;
}
