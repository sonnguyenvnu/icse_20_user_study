@Override public final User authenticate(AuthenticationInfo authenticationInfo,Database database) throws AuthenticationException {
  String userName=authenticationInfo.getFullyQualifiedName();
  User user=database.findUser(userName);
  if (user == null && !isAllowUserRegistration()) {
    throw new AuthenticationException("User " + userName + " not found in db");
  }
  CredentialsValidator validator=realms.get(authenticationInfo.getRealm());
  if (validator == null) {
    throw new AuthenticationException("realm " + authenticationInfo.getRealm() + " not configured");
  }
  try {
    if (!validator.validateCredentials(authenticationInfo)) {
      return null;
    }
  }
 catch (  Exception e) {
    throw new AuthenticationException(e);
  }
  if (user == null) {
synchronized (database.getSystemSession()) {
      user=UserBuilder.buildUser(authenticationInfo,database,isPersistUsers());
      database.addDatabaseObject(database.getSystemSession(),user);
      database.getSystemSession().commit(false);
    }
  }
  user.revokeTemporaryRightsOnRoles();
  updateRoles(authenticationInfo,user,database);
  return user;
}
