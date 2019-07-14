private static HttpURI getHttpUri(Request request){
  try {
    return (HttpURI)getURIMethodFromClass(request.getClass()).invoke(request);
  }
 catch (  Exception e) {
    throw new IllegalArgumentException(request + " does not have a getUri or getHttpURI method");
  }
}
