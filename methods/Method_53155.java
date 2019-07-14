private void ensureTokenIsAvailable(){
  if (null == oauthToken) {
    throw new IllegalStateException("No Token available.");
  }
}
