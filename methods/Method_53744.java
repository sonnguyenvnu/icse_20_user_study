public static void checkURL(URL url){
  String[] location=new String[1];
  int status=checkURL(url,"HEAD",location);
  if (status == HttpURLConnection.HTTP_FORBIDDEN) {
    status=checkURL(url,"GET",location);
  }
  if (status == HttpURLConnection.HTTP_OK) {
    return;
  }
  if (!url.toString().startsWith("http://docs.gl")) {
    System.err.println(status == HttpURLConnection.HTTP_MOVED_PERM ? "301: " + url + "\n  -> " + location[0] : status + ": " + url);
  }
}
