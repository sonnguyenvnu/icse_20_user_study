/** 
 * Encodes user/password/salt information in the following way: MD5(MD5(password + user) + salt).
 * @param user The connecting user.
 * @param password The connecting user's password.
 * @param salt A four-salt sent by the server.
 * @return A 35-byte array, comprising the string "md5" and an MD5 digest.
 */
public static byte[] encode(byte[] user,byte[] password,byte[] salt){
  MessageDigest md;
  byte[] tempDigest;
  byte[] passDigest;
  byte[] hexDigest=new byte[35];
  try {
    md=MessageDigest.getInstance("MD5");
    md.update(password);
    md.update(user);
    tempDigest=md.digest();
    bytesToHex(tempDigest,hexDigest,0);
    md.update(hexDigest,0,32);
    md.update(salt);
    passDigest=md.digest();
    bytesToHex(passDigest,hexDigest,3);
    hexDigest[0]=(byte)'m';
    hexDigest[1]=(byte)'d';
    hexDigest[2]=(byte)'5';
  }
 catch (  NoSuchAlgorithmException e) {
    throw new IllegalStateException("Unable to encode password with MD5",e);
  }
  return hexDigest;
}
