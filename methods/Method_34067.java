public boolean isAuthenticationRequired(){
  return StringUtils.hasText(clientId) && clientAuthenticationScheme != AuthenticationScheme.none;
}
