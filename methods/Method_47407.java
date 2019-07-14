/** 
 * Parse path to decrypt smb password
 */
public static String getSmbDecryptedPath(Context context,String path) throws GeneralSecurityException, IOException {
  if (!(path.contains(":") && path.contains("@"))) {
    return path;
  }
  StringBuilder buffer=new StringBuilder();
  buffer.append(path.substring(0,path.indexOf(":",4) + 1));
  String encryptedPassword=path.substring(path.indexOf(":",4) + 1,path.lastIndexOf("@"));
  if (!TextUtils.isEmpty(encryptedPassword)) {
    String decryptedPassword=CryptUtil.decryptPassword(context,encryptedPassword);
    buffer.append(decryptedPassword);
  }
  buffer.append(path.substring(path.lastIndexOf("@"),path.length()));
  return buffer.toString();
}
