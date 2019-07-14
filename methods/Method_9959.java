private static void refreshBaiduAccessToken(){
  if (StringUtils.isBlank(BAIDU_API_KEY) || StringUtils.isBlank(BAIDU_SECRET_KEY)) {
    return;
  }
  final long now=System.currentTimeMillis();
  if (StringUtils.isNotBlank(BAIDU_ACCESS_TOKEN) && now - 1000 * 60 * 60 * 6 < BAIDU_ACCESS_TOKEN_TIME) {
    return;
  }
  try {
    final URL url=new URL("https://openapi.baidu.com/oauth/2.0/token?grant_type=client_credentials" + "&client_id=" + BAIDU_API_KEY + "&client_secret=" + BAIDU_SECRET_KEY);
    final HttpURLConnection conn=(HttpURLConnection)url.openConnection();
    conn.setRequestMethod("POST");
    try (final InputStream inputStream=conn.getInputStream()){
      final String content=IOUtils.toString(inputStream,"UTF-8");
      conn.disconnect();
      final JSONObject result=new JSONObject(content);
      BAIDU_ACCESS_TOKEN=result.optString("access_token",null);
      BAIDU_ACCESS_TOKEN_TIME=now;
    }
   }
 catch (  final Exception e) {
    LOGGER.log(Level.ERROR,"Requires Baidu Yuyin access token failed",e);
  }
}
