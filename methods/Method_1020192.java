public String exec(){
  HttpURLConnection http=null;
  try {
    http=(HttpURLConnection)new URL(baseUrl + (req == null ? "" : req) + (params.length() > 0 ? ("?" + params) : "")).openConnection();
    http.setRequestProperty("Accept-Charset","UTF-8");
    HttpURLConnection.setFollowRedirects(false);
    http.setConnectTimeout(5 * 1000);
    http.setReadTimeout(5 * 1000);
    http.connect();
    int status=http.getResponseCode();
    String charset=getCharset(http.getHeaderField("Content-Type"));
    if (status == 200) {
      return readResponseBody(http,charset);
    }
 else {
      logger.warn("non 200 respoonse :" + readErrorResponseBody(http,status,charset));
      return null;
    }
  }
 catch (  Exception e) {
    logger.error("exec error {}",e.getMessage());
    return null;
  }
 finally {
    if (http != null)     http.disconnect();
  }
}
