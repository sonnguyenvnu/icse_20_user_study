@Override public void initialize(HttpRequest httpRequest){
  final HttpUnsuccessfulResponseHandler backoffHandler=new HttpBackOffUnsuccessfulResponseHandler(new ExponentialBackOff.Builder().setMaxElapsedTimeMillis(((int)maxWait.getMillis())).build()).setSleeper(sleeper);
  httpRequest.setInterceptor(wrappedCredential);
  httpRequest.setUnsuccessfulResponseHandler(new HttpUnsuccessfulResponseHandler(){
    @Override public boolean handleResponse(    HttpRequest request,    HttpResponse response,    boolean supportsRetry) throws IOException {
      if (wrappedCredential.handleResponse(request,response,supportsRetry)) {
        return true;
      }
 else       if (backoffHandler.handleResponse(request,response,supportsRetry)) {
        logger.debug("Retrying [{}] times : [{}]",retry,request.getUrl());
        return true;
      }
 else {
        return false;
      }
    }
  }
);
  httpRequest.setIOExceptionHandler(new HttpBackOffIOExceptionHandler(new ExponentialBackOff.Builder().setMaxElapsedTimeMillis(((int)maxWait.getMillis())).build()).setSleeper(sleeper));
}
