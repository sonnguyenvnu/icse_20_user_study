/** 
 * Lists credentials stored in the credsStore.
 * @param credsStore Name of the docker credential helper
 * @return Map of registries to auth identifiers.(For instance, usernames for which you have signed in.)
 * @throws IOException When we cannot read from the credential helper
 */
public static Map<String,String> list(final String credsStore) throws IOException {
  return credentialHelperDelegate.list(credsStore);
}
