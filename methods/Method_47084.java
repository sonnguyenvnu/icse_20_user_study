/** 
 * Convenience method to call  {@link SmbUtil#getSmbDecryptedPath(Context,String)} if the givenSSH URL contains the password (assuming the password is encrypted).
 * @param fullUri SSH URL
 * @return SSH URL with the password (if exists) decrypted
 */
public static final String decryptSshPathAsNecessary(@NonNull String fullUri){
  String uriWithoutProtocol=fullUri.substring(SSH_URI_PREFIX.length(),fullUri.lastIndexOf('@'));
  try {
    return (uriWithoutProtocol.lastIndexOf(':') > 0) ? SmbUtil.getSmbDecryptedPath(AppConfig.getInstance(),fullUri) : fullUri;
  }
 catch (  IOException|GeneralSecurityException e) {
    Log.e(TAG,"Error decrypting path",e);
    return fullUri;
  }
}
