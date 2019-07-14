protected String buildUrl(){
  if (url == null) {
    throw new IllegalStateException("'url' must not be null.");
  }
  return String.format("%s/room/%s/notification?auth_token=%s",url.toString(),roomId,authToken);
}
