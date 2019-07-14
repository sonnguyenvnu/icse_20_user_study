public static LoggedRequest createFrom(Request request){
  return new LoggedRequest(request.getUrl(),request.getAbsoluteUrl(),request.getMethod(),request.getClientIp(),copyOf(request.getHeaders()),ImmutableMap.copyOf(request.getCookies()),request.isBrowserProxyRequest(),new Date(),request.getBody(),request.getParts());
}
