/** 
 * Generate the impersonated user credentials thanks to the S4U2self mechanism
 * @return the client impersonated GSSCredential
 * @throws PrivilegedActionException in case of failure
 */
private static GSSCredential impersonate() throws PrivilegedActionException {
  return Subject.doAs(serviceSubject,(PrivilegedExceptionAction<GSSCredential>)() -> {
    GSSManager manager=GSSManager.getInstance();
    GSSCredential self=manager.createCredential(null,GSSCredential.DEFAULT_LIFETIME,krb5Oid,GSSCredential.INITIATE_ONLY);
    GSSName user=manager.createName(TARGET_USER_NAME,GSSName.NT_USER_NAME);
    return ((ExtendedGSSCredential)self).impersonate(user);
  }
);
}
