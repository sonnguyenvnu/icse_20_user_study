private static byte[] baiduTTS(final String text,final String uid){
  if (StringUtils.isBlank(BAIDU_API_KEY) || StringUtils.isBlank(BAIDU_SECRET_KEY)) {
    return null;
  }
  if (null == BAIDU_ACCESS_TOKEN) {
    refreshBaiduAccessToken();
  }
  if (null == BAIDU_ACCESS_TOKEN) {
    LOGGER.warn("Please configure [baidu.yuyin.*] in symphony.properties");
    return null;
  }
  try {
    final URL url=new URL("http://tsn.baidu.com/text2audio?grant_type=client_credentials" + "&client_id=" + BAIDU_API_KEY + "&client_secret=" + BAIDU_SECRET_KEY);
    final HttpURLConnection conn=(HttpURLConnection)url.openConnection();
    conn.setRequestMethod("POST");
    conn.setDoOutput(true);
    try (final OutputStream outputStream=conn.getOutputStream()){
      IOUtils.write("tex=" + URLs.encode(StringUtils.substring(text,0,1024)) + "&lan=zh&cuid=" + uid + "&spd=6&pit=6&ctp=1&tok=" + BAIDU_ACCESS_TOKEN,outputStream,"UTF-8");
    }
     byte[] ret;
    try (final InputStream inputStream=conn.getInputStream()){
      final int responseCode=conn.getResponseCode();
      final String contentType=conn.getContentType();
      if (HttpServletResponse.SC_OK != responseCode || !"audio/mp3".equals(contentType)) {
        final String msg=IOUtils.toString(inputStream,"UTF-8");
        LOGGER.warn("Baidu Yuyin TTS failed: " + msg);
        conn.disconnect();
        return null;
      }
      ret=IOUtils.toByteArray(inputStream);
    }
     conn.disconnect();
    return ret;
  }
 catch (  final Exception e) {
    LOGGER.log(Level.ERROR,"Requires Baidu Yuyin access token failed",e);
  }
  return null;
}
