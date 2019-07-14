private void makeRequestWithRetries() throws IOException {
  boolean retry=true;
  IOException cause=null;
  HttpRequestRetryHandler retryHandler=client.getHttpRequestRetryHandler();
  try {
    while (retry) {
      try {
        makeRequest();
        return;
      }
 catch (      UnknownHostException e) {
        cause=new IOException("UnknownHostException exception: " + e.getMessage(),e);
        retry=(executionCount > 0) && retryHandler.retryRequest(e,++executionCount,context);
      }
catch (      NullPointerException e) {
        cause=new IOException("NPE in HttpClient: " + e.getMessage());
        retry=retryHandler.retryRequest(cause,++executionCount,context);
      }
catch (      IOException e) {
        if (isCancelled()) {
          return;
        }
        cause=e;
        retry=retryHandler.retryRequest(cause,++executionCount,context);
      }
      if (retry) {
        responseHandler.sendRetryMessage(executionCount);
      }
    }
  }
 catch (  Exception e) {
    AsyncHttpClient.log.e("AsyncHttpRequest","Unhandled exception origin cause",e);
    cause=new IOException("Unhandled exception: " + e.getMessage(),cause);
  }
  throw (cause);
}
