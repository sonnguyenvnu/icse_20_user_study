public static String trace(String path,Map<String,String> params,Map<String,String> headers) throws Exception {
  path=getPath(path,params);
  HttpTrace method=new HttpTrace(path);
  RequestConfig requestConfig=RequestConfig.custom().setSocketTimeout(3000).setConnectTimeout(3000).setConnectionRequestTimeout(3000).setStaleConnectionCheckEnabled(true).build();
  method.setConfig(requestConfig);
  return getResponse(method,headers);
}
