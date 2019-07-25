private void trace(final String method,final StreamResponse response,final Map<String,String> wireAttrs,final RequestContext requestContext){
  final URI requestUri=(URI)requestContext.getLocalAttr(REQUEST_URI);
  final String requestMethod=(String)requestContext.getLocalAttr(REQUEST_METHOD);
  Callback<Integer> callback=new Callback<Integer>(){
    @Override public void onError(    Throwable e){
      _log.warn("Cannot get the length of the response",e);
    }
    @Override public void onSuccess(    Integer result){
      _log.debug(buildLogMessage(method,"response",formatResponse(response,result,requestUri,requestMethod),wireAttrs,requestContext));
    }
  }
;
  response.getEntityStream().addObserver(new LengthObserver(callback));
}
