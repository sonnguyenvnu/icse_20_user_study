public static String httpGet(String url,Map params){
  return httpRequest(url,params,HTTP_METHOD_GET,DEFAULT_CHARSET,null);
}
