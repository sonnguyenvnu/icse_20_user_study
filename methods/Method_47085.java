/** 
 * Convenience method to extract the Base URL from the given SSH URL. For example, given <code>ssh://user:password@127.0.0.1:22/home/user/foo/bar</code>, this method returns <code>ssh://user:password@127.0.0.1:22</code>.
 * @param fullUri Full SSH URL
 * @return The remote path part of the full SSH URL
 */
public static final String extractBaseUriFrom(@NonNull String fullUri){
  String uriWithoutProtocol=fullUri.substring(SSH_URI_PREFIX.length());
  return uriWithoutProtocol.indexOf('/') == -1 ? fullUri : fullUri.substring(0,uriWithoutProtocol.indexOf('/') + SSH_URI_PREFIX.length());
}
