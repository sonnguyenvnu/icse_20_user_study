public static HttpUriRequest getHttpRequestFor(RequestMethod method,String url){
  notifier().info("Proxying: " + method + " " + url);
  if (method.equals(GET))   return new HttpGet(url);
 else   if (method.equals(POST))   return new HttpPost(url);
 else   if (method.equals(PUT))   return new HttpPut(url);
 else   if (method.equals(DELETE))   return new HttpDelete(url);
 else   if (method.equals(HEAD))   return new HttpHead(url);
 else   if (method.equals(OPTIONS))   return new HttpOptions(url);
 else   if (method.equals(TRACE))   return new HttpTrace(url);
 else   if (method.equals(PATCH))   return new HttpPatch(url);
 else   return new GenericHttpUriRequest(method.toString(),url);
}
