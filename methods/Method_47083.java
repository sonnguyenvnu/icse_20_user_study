/** 
 * Convenience method to call  {@link SmbUtil#getSmbEncryptedPath(Context,String)} if the givenSSH URL contains the password (assuming the password is encrypted).
 * @param fullUri SSH URL
 * @return SSH URL with the password (if exists) encrypted
 */
public static final String encryptSshPathAsNecessary(@NonNull String fullUri){
  String uriWithoutProtocol=fullUri.substring(SSH_URI_PREFIX.length(),fullUri.lastIndexOf('@'));
  try {
    return (uriWithoutProtocol.lastIndexOf(':') > 0) ? SmbUtil.getSmbEncryptedPath(AppConfig.getInstance(),fullUri) : fullUri;
  }
 catch (  IOException|GeneralSecurityException e) {
    Log.e(TAG,"Error encrypting path",e);
    return fullUri;
  }
}
