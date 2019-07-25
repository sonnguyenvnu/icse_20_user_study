private void trace(final String method,final StreamRequest request,final Map<String,String> wireAttrs,final RequestContext requestContext){
  Callback<Integer> callback=new Callback<Integer>(){
    @Override public void onError(    Throwable e){
      _log.warn("Cannot get the length of the request",e);
    }
    @Override public void onSuccess(    Integer result){
      _log.debug(buildLogMessage(method,"request",formatRequest(request,result),wireAttrs,requestContext));
    }
  }
;
  request.getEntityStream().addObserver(new LengthObserver(callback));
}
