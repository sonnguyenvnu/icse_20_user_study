static private String getFTid(String url){
  if (url == null) {
    return null;
  }
  int equal=url.lastIndexOf('=');
  if (equal < 0) {
    return null;
  }
  return url.substring(equal + 1);
}
