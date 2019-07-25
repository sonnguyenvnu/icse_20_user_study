public static byte[] salting(String pass,byte[] salt) throws NoSuchAlgorithmException, InvalidKeySpecException {
  final int iterations=500;
  final int keyLength=512;
  final SecretKeyFactory skf=SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
  final PBEKeySpec spec=new PBEKeySpec(pass.toCharArray(),salt,iterations,keyLength);
  final SecretKey key=skf.generateSecret(spec);
  final byte[] tmp=key.getEncoded();
  return tmp;
}
