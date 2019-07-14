public static String doPost(String reqUrl,Map<String,String> parameters,String encoding,int connectTimeout,int readTimeout){
  HttpURLConnection urlConn=null;
  try {
    urlConn=sendPost(reqUrl,parameters,encoding,connectTimeout,readTimeout);
    String responseContent=getContent(urlConn,encoding);
    return responseContent.trim();
  }
  finally {
    if (urlConn != null) {
      urlConn.disconnect();
      urlConn=null;
    }
  }
}
