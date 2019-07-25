public boolean authenticate(AuthToken token,Message msg){
  if ((token != null) && (token instanceof MD5Token)) {
    MD5Token serverToken=(MD5Token)token;
    return (this.auth_value != null) && (serverToken.auth_value != null) && (this.auth_value.equalsIgnoreCase(serverToken.auth_value));
  }
  log.warn("Invalid AuthToken instance - wrong type or null");
  return false;
}
