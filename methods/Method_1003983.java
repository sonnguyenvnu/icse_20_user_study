/** 
 * Creates a configuration from command line arguments.
 * @return Configuration instance.
 */
public static ClientConfig create(){
  return new ClientConfig(ZK_ENDPOINTS.get(),Optional.fromNullable(CHROOT_PATH.get()),IN_PROCESS.get(),SESSION_TIMEOUT.get(),DIGEST_CREDENTIALS.hasAppliedValue() ? getCredentials(DIGEST_CREDENTIALS.get()) : Credentials.NONE);
}
