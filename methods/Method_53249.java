final void ensureOAuthEnabled(){
  if (!(auth instanceof OAuthAuthorization)) {
    throw new IllegalStateException("OAuth required. Authentication credentials are missing. " + WWW_DETAILS);
  }
}
