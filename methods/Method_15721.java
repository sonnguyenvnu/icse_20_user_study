@Override public Authentication authenticate(AuthenticationRequest request){
  if (request instanceof PlainTextUsernamePasswordAuthenticationRequest) {
    return sync(users.values().stream().filter(user -> ((PlainTextUsernamePasswordAuthenticationRequest)request).getUsername().equals(user.getUsername()) && ((PlainTextUsernamePasswordAuthenticationRequest)request).getPassword().equals(user.getPassword())).findFirst().map(properties -> authentications.get(properties.getId())).orElseThrow(() -> new ValidationException("?????")));
  }
  throw new UnsupportedOperationException("????????:" + request);
}
