public boolean authenticate(AuthToken token,Message msg){
  if ((token != null) && (token instanceof SimpleToken)) {
    SimpleToken serverToken=(SimpleToken)token;
    if ((this.auth_value != null) && (serverToken.auth_value != null) && (this.auth_value.equalsIgnoreCase(serverToken.auth_value))) {
      if (log.isDebugEnabled()) {
        log.debug("SimpleToken match");
      }
      return true;
    }
 else {
      return false;
    }
  }
  if (log.isWarnEnabled()) {
    log.warn("Invalid AuthToken instance - wrong type or null");
  }
  return false;
}
