private static HttpURLConnection sendPost(String reqUrl,Map<String,String> parameters,String encoding,int connectTimeout,int readTimeout){
  HttpURLConnection urlConn=null;
  try {
    String params=generatorParamString(parameters,encoding);
    URL url=new URL(reqUrl);
    urlConn=(HttpURLConnection)url.openConnection();
    urlConn.setRequestMethod("POST");
    urlConn.setConnectTimeout(connectTimeout);
    urlConn.setReadTimeout(readTimeout);
    urlConn.setDoOutput(true);
    byte[] b=params.getBytes(encoding);
    urlConn.getOutputStream().write(b,0,b.length);
    urlConn.getOutputStream().flush();
    urlConn.getOutputStream().close();
  }
 catch (  Exception e) {
    logger.error(e.getMessage(),e);
  }
  return urlConn;
}
