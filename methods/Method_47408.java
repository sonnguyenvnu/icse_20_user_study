/** 
 * Parse path to encrypt smb password
 */
public static String getSmbEncryptedPath(Context context,String path) throws GeneralSecurityException, IOException {
  if (!(path.contains(":") && path.contains("@"))) {
    return path;
  }
  StringBuilder buffer=new StringBuilder();
  buffer.append(path.substring(0,path.indexOf(":",4) + 1));
  String decryptedPassword=path.substring(path.indexOf(":",4) + 1,path.lastIndexOf("@"));
  if (!TextUtils.isEmpty(decryptedPassword)) {
    String encryptPassword=CryptUtil.encryptPassword(context,decryptedPassword);
    buffer.append(encryptPassword);
  }
  buffer.append(path.substring(path.lastIndexOf("@"),path.length()));
  return buffer.toString();
}
