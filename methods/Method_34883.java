@Override public void process(HttpRequest request,HttpContext context){
  AuthState authState=(AuthState)context.getAttribute(HttpClientContext.TARGET_AUTH_STATE);
  if (authState.getAuthScheme() == null) {
    CredentialsProvider credsProvider=(CredentialsProvider)context.getAttribute(HttpClientContext.CREDS_PROVIDER);
    HttpHost targetHost=(HttpHost)context.getAttribute(HttpCoreContext.HTTP_TARGET_HOST);
    Credentials credentials=credsProvider.getCredentials(new AuthScope(targetHost.getHostName(),targetHost.getPort()));
    if (credentials != null) {
      authState.update(new BasicScheme(),credentials);
    }
  }
}
