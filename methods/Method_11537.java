private RequestBuilder selectRequestMethod(Request request){
  String method=request.getMethod();
  if (method == null || method.equalsIgnoreCase(HttpConstant.Method.GET)) {
    return RequestBuilder.get();
  }
 else   if (method.equalsIgnoreCase(HttpConstant.Method.POST)) {
    return addFormParams(RequestBuilder.post(),request);
  }
 else   if (method.equalsIgnoreCase(HttpConstant.Method.HEAD)) {
    return RequestBuilder.head();
  }
 else   if (method.equalsIgnoreCase(HttpConstant.Method.PUT)) {
    return addFormParams(RequestBuilder.put(),request);
  }
 else   if (method.equalsIgnoreCase(HttpConstant.Method.DELETE)) {
    return RequestBuilder.delete();
  }
 else   if (method.equalsIgnoreCase(HttpConstant.Method.TRACE)) {
    return RequestBuilder.trace();
  }
  throw new IllegalArgumentException("Illegal HTTP Method " + method);
}
