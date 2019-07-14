private static AndroidHttpClient createHttpClient(Context context){
  String userAgent=MmsConfig.getUserAgent();
  AndroidHttpClient client=AndroidHttpClient.newInstance(userAgent,context);
  HttpParams params=client.getParams();
  HttpProtocolParams.setContentCharset(params,"UTF-8");
  int soTimeout=MmsConfig.getHttpSocketTimeout();
  Timber.d("[HttpUtils] createHttpClient w/ socket timeout " + soTimeout + " ms, " + ", UA=" + userAgent);
  HttpConnectionParams.setSoTimeout(params,soTimeout);
  return client;
}
