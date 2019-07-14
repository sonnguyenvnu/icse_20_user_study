@NonNull @Override public Request authenticate(Request request) throws IOException {
  String authToken=getAuthToken();
  return request.newBuilder().header(Http.Headers.AUTHORIZATION,Http.Headers.makeBearerAuthorization(authToken)).build();
}
