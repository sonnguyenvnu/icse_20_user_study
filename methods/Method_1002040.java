@Override public Optional<User> authenticate(BasicCredentials credentials){
  User user=null;
  try {
    String username=credentials.getUsername();
    if (!User.isSanitizedUsername(username)) {
      logger.info("Username: {} must match pattern: {}",username,User.USERNAME_PATTERN);
      return Optional.empty();
    }
    String userDN=dnFromUsername(username);
    String password=credentials.getPassword();
    if (Strings.isNullOrEmpty(password)) {
      logger.info("No password for user provided");
      return Optional.empty();
    }
    LDAPConnection authenticatedConnection=connectionFactory.getLDAPConnection(userDN,password);
    authenticatedConnection.close();
    Set<String> requiredRoles=config.getRequiredRoles();
    if (!requiredRoles.isEmpty()) {
      Set<String> roles=rolesFromDN(userDN);
      boolean accessAllowed=false;
      for (      String requiredRole : requiredRoles) {
        if (roles.contains(requiredRole)) {
          accessAllowed=true;
        }
      }
      if (!accessAllowed) {
        logger.warn("User {} not in one of required LDAP roles: [{}].",username,requiredRoles);
        throw new ForbiddenException();
      }
    }
    user=User.named(username);
  }
 catch (  LDAPException le) {
    if (le.getResultCode() != ResultCode.INVALID_CREDENTIALS) {
      logger.error("Error connecting to LDAP",le);
      throw Throwables.propagate(le);
    }
  }
catch (  GeneralSecurityException gse) {
    logger.error("TLS error connecting to LDAP",gse);
    throw Throwables.propagate(gse);
  }
  return Optional.ofNullable(user);
}
