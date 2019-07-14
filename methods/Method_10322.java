public void process(final HttpRequest request,final HttpContext context) throws HttpException, IOException {
  AuthState authState=(AuthState)context.getAttribute(ClientContext.TARGET_AUTH_STATE);
  CredentialsProvider credsProvider=(CredentialsProvider)context.getAttribute(ClientContext.CREDS_PROVIDER);
  HttpHost targetHost=(HttpHost)context.getAttribute(ExecutionContext.HTTP_TARGET_HOST);
  if (authState.getAuthScheme() == null) {
    AuthScope authScope=new AuthScope(targetHost.getHostName(),targetHost.getPort());
    Credentials creds=credsProvider.getCredentials(authScope);
    if (creds != null) {
      authState.setAuthScheme(new BasicScheme());
      authState.setCredentials(creds);
    }
  }
}
