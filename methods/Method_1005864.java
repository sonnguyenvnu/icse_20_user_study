public static String put(String path,Map<String,String> params,Map<String,String> headers) throws Exception {
  HttpPut method=new HttpPut(path);
  RequestConfig requestConfig=RequestConfig.custom().setSocketTimeout(3000).setConnectTimeout(3000).setConnectionRequestTimeout(3000).setStaleConnectionCheckEnabled(true).build();
  List<NameValuePair> pairs=buildPairs(params);
  if (pairs.size() > 0) {
    HttpEntity entity=new UrlEncodedFormEntity(pairs,"utf-8");
    method.setEntity(entity);
  }
  method.setConfig(requestConfig);
  return getResponse(method,headers);
}
