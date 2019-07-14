public static ResponseDefinition browserProxy(Request originalRequest){
  final ResponseDefinition response=new ResponseDefinition();
  response.browserProxyUrl=originalRequest.getAbsoluteUrl();
  return response;
}
