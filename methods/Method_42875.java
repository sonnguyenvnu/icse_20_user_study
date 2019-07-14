@Override public String digestParams(RestInvocation restInvocation){
  String method=restInvocation.getHttpMethod();
  String path=stripParams(restInvocation.getPath());
  String query=Stream.of(restInvocation.getParamsMap().get(PathParam.class),restInvocation.getParamsMap().get(FormParam.class)).map(Params::asHttpHeaders).map(Map::entrySet).flatMap(Collection::stream).filter(e -> !"signature".equals(e.getKey())).sorted(Entry.comparingByKey()).map(e -> e.getKey() + "=" + e.getValue()).collect(Collectors.joining("&"));
  String toSign=String.format("%s|/api/v2/%s|%s",method,path,query);
  Mac sha256hmac=getMac();
  byte[] signed=sha256hmac.doFinal(toSign.getBytes());
  String signature=new String(encodeHex(signed));
  replaceInvocationUrl(restInvocation,signature);
  return signature;
}
