private String getPathWithoutLeadingSlash(URL entry){
  final String path=entry.getPath();
  return path.startsWith("/") ? path.substring(1) : path;
}
