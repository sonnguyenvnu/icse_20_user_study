private UserGroupInformation login(Configuration hadoopConfiguration,Settings repositorySettings){
  AuthenticationMethod authMethod=SecurityUtil.getAuthenticationMethod(hadoopConfiguration);
  if (authMethod.equals(AuthenticationMethod.SIMPLE) == false && authMethod.equals(AuthenticationMethod.KERBEROS) == false) {
    throw new RuntimeException("Unsupported authorization mode [" + authMethod + "]");
  }
  String kerberosPrincipal=repositorySettings.get(CONF_SECURITY_PRINCIPAL);
  if (kerberosPrincipal != null && authMethod.equals(AuthenticationMethod.SIMPLE)) {
    LOGGER.warn("Hadoop authentication method is set to [SIMPLE], but a Kerberos principal is " + "specified. Continuing with [KERBEROS] authentication.");
    SecurityUtil.setAuthenticationMethod(AuthenticationMethod.KERBEROS,hadoopConfiguration);
  }
 else   if (kerberosPrincipal == null && authMethod.equals(AuthenticationMethod.KERBEROS)) {
    throw new RuntimeException("HDFS Repository does not support [KERBEROS] authentication without " + "a valid Kerberos principal and keytab. Please specify a principal in the repository settings with [" + CONF_SECURITY_PRINCIPAL + "].");
  }
  UserGroupInformation.setConfiguration(hadoopConfiguration);
  LOGGER.debug("Hadoop security enabled: [{}]",UserGroupInformation.isSecurityEnabled());
  LOGGER.debug("Using Hadoop authentication method: [{}]",SecurityUtil.getAuthenticationMethod(hadoopConfiguration));
  try {
    if (UserGroupInformation.isSecurityEnabled()) {
      String principal=preparePrincipal(kerberosPrincipal);
      String keytab=HdfsSecurityContext.locateKeytabFile(environment).toString();
      LOGGER.debug("Using kerberos principal [{}] and keytab located at [{}]",principal,keytab);
      return UserGroupInformation.loginUserFromKeytabAndReturnUGI(principal,keytab);
    }
    return UserGroupInformation.getCurrentUser();
  }
 catch (  IOException e) {
    throw new UncheckedIOException("Could not retrieve the current user information",e);
  }
}
