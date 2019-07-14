public String createAuthorizationCode(OAuth2Authentication authentication){
  String code=generator.generate();
  store(code,authentication);
  return code;
}
