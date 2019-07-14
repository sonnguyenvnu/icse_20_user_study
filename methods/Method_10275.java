/** 
 * Sets HttpRequestInterceptor which handles authorization in preemptive way, as workaround you can use call `AsyncHttpClient.addHeader("Authorization","Basic base64OfUsernameAndPassword==")`
 * @param isPreemptive whether the authorization is processed in preemptive way
 */
public void setAuthenticationPreemptive(boolean isPreemptive){
  if (isPreemptive) {
    httpClient.addRequestInterceptor(new PreemptiveAuthorizationHttpRequestInterceptor(),0);
  }
 else {
    httpClient.removeRequestInterceptorByClass(PreemptiveAuthorizationHttpRequestInterceptor.class);
  }
}
