static String fixURL(boolean useSSL,String url){
  if (null == url) {
    return null;
  }
  int index=url.indexOf("://");
  if (-1 == index) {
    throw new IllegalArgumentException("url should contain '://'");
  }
  String hostAndLater=url.substring(index + 3);
  if (useSSL) {
    return "https://" + hostAndLater;
  }
 else {
    return "http://" + hostAndLater;
  }
}
