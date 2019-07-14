static public String getAuthorizationUrl(ButterflyModule module,HttpServletRequest request) throws MalformedURLException {
  String authorizedUrl=makeRedirectUrl(module,request);
  GoogleAuthorizationCodeRequestUrl url=new GoogleAuthorizationCodeRequestUrl(CLIENT_ID,authorizedUrl,Arrays.asList(SCOPES));
  return url.toString();
}
