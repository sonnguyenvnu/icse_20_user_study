@Override public Authenticator<BasicCredentials,User> build(DSLContext dslContext){
  logger.debug("Creating LDAP authenticator");
  LdapConnectionFactory connectionFactory=new LdapConnectionFactory(getServer(),getPort(),getUserDN(),getPassword(),getTrustStorePath(),getTrustStorePassword(),getTrustStoreType());
  return new LdapAuthenticator(connectionFactory,getLookup());
}
