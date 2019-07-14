public static String getPath(String url){
  return url.contains("?") ? url.substring(0,url.indexOf("?")) : url;
}
