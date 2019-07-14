final void ensureAuthorizationEnabled(){
  if (!auth.isEnabled()) {
    throw new IllegalStateException("Authentication credentials are missing. " + WWW_DETAILS);
  }
}
