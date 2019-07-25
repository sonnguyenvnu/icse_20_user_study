/** 
 * Creates a new  {@link RestClient} based on the provided configuration.
 */
public RestClient build(){
  if (failureListener == null) {
    failureListener=new RestClient.FailureListener();
  }
  CloseableHttpAsyncClient httpClient=AccessController.doPrivileged(new PrivilegedAction<CloseableHttpAsyncClient>(){
    @Override public CloseableHttpAsyncClient run(){
      return createHttpClient();
    }
  }
);
  RestClient restClient=new RestClient(httpClient,maxRetryTimeout,defaultHeaders,hosts,pathPrefix,failureListener);
  httpClient.start();
  return restClient;
}
