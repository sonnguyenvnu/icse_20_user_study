/** 
 * ??base32???????
 * @param length
 * @return
 */
public static String getRandomSecretBase32(int length){
  SecureRandom random=new SecureRandom();
  byte[] salt=new byte[length / 2];
  random.nextBytes(salt);
  return Base32String.encode(salt);
}
