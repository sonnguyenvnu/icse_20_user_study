/** 
 * Store an auth value in the credsStore.
 * @param credsStore Name of the docker credential helper
 * @param auth Auth object to store
 * @return Exit code of the process
 * @throws IOException When we cannot read from the credential helper
 * @throws InterruptedException When writing to the credential helperis interrupted
 */
public static int store(final String credsStore,final DockerCredentialHelperAuth auth) throws IOException, InterruptedException {
  return credentialHelperDelegate.store(credsStore,auth);
}
