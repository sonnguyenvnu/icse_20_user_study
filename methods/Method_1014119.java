@Modified void modified(Map<String,Object> properties){
  Object authenticationEnabled=properties.get(AUTHENTICATION_ENABLED);
  if (authenticationEnabled != null) {
    this.enabled=Boolean.valueOf(authenticationEnabled.toString());
  }
  Object loginUri=properties.get(AUTHENTICATION_ENDPOINT);
  if (loginUri != null && loginUri instanceof String) {
    this.loginUri=(String)loginUri;
  }
}
