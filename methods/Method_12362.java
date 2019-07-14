protected String getScheme(@Nullable Ssl ssl){
  return ssl != null && ssl.isEnabled() ? "https" : "http";
}
