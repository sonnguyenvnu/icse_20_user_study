@Nullable @Override public Request retryAuthentication(Response response) throws IOException {
  if (!shouldRetryAuthentication(response)) {
    return null;
  }
  Request oldRequest=response.request();
  String oldAuthorization=oldRequest.header(Http.Headers.AUTHORIZATION);
  String oldAuthToken=Http.Headers.getTokenFromBearerAuthorization(oldAuthorization);
  if (oldAuthToken != null) {
    invalidateAuthToken(oldAuthToken);
  }
  return authenticate(oldRequest);
}
