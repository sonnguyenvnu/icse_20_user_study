public boolean authenticate(AuthToken token,Message msg){
  if (!isAuthenticated()) {
    log.error(Util.getMessage("Krb5TokenFailedToSetupCorrectlyCannotAuthenticateAnyPeers"));
    return false;
  }
  if ((token != null) && token instanceof Krb5Token) {
    Krb5Token remoteToken=(Krb5Token)token;
    try {
      validateRemoteServiceTicket(remoteToken);
      return true;
    }
 catch (    Exception e) {
      log.error(Util.getMessage("Krb5TokenServiceTicketValidationFailed"),e);
      return false;
    }
  }
  return false;
}
