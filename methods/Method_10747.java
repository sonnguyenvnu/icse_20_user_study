/** 
 * ?data??algorithm????
 * @param data      ??????
 * @param algorithm ????
 * @return ??????
 */
private static byte[] encryptAlgorithm(byte[] data,String algorithm){
  try {
    MessageDigest md=MessageDigest.getInstance(algorithm);
    md.update(data);
    return md.digest();
  }
 catch (  NoSuchAlgorithmException e) {
    e.printStackTrace();
  }
  return new byte[0];
}
