/** 
 * @param url
 * @return
 */
private OkHttpClient getHttpClient(String url){
  Log.i(TAG,"getHttpClient  url = " + url);
  if (StringUtil.isNotEmpty(url,true) == false) {
    Log.e(TAG,"getHttpClient  StringUtil.isNotEmpty(url, true) == false >> return null;");
    return null;
  }
  OkHttpClient client=new OkHttpClient();
  client.setCookieHandler(new HttpHead());
  client.setConnectTimeout(15,TimeUnit.SECONDS);
  client.setWriteTimeout(10,TimeUnit.SECONDS);
  client.setReadTimeout(10,TimeUnit.SECONDS);
  return client;
}
