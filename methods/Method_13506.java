public static SentinelClientHttpResponse handleException(HttpRequest request,byte[] body,ClientHttpRequestExecution execution,BlockException ex){
  System.out.println("Oops: " + ex.getClass().getCanonicalName());
  return new SentinelClientHttpResponse("custom block info");
}
