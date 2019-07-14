@Override public ResponseHandlerInterface getResponseHandler(){
  return new BinaryHttpResponseHandler(){
    @Override public void onStart(){
      clearOutputs();
    }
    @Override public String[] getAllowedContentTypes(){
      return new String[]{".*"};
    }
    public void onSuccess(    int statusCode,    Header[] headers,    byte[] binaryData){
      debugStatusCode(LOG_TAG,statusCode);
      debugHeaders(LOG_TAG,headers);
      debugResponse(LOG_TAG,"Received response is " + binaryData.length + " bytes");
    }
    @Override public void onFailure(    int statusCode,    Header[] headers,    byte[] errorResponse,    Throwable e){
      debugHeaders(LOG_TAG,headers);
      debugStatusCode(LOG_TAG,statusCode);
      debugThrowable(LOG_TAG,e);
      if (errorResponse != null) {
        debugResponse(LOG_TAG,"Received response is " + errorResponse.length + " bytes");
      }
    }
  }
;
}
