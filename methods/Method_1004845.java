public static EncryptionContext create(String secretKey) throws NoSuchAlgorithmException {
  SecureRandom randomSecureRandom=SecureRandom.getInstance("SHA1PRNG");
  byte[] iv=new byte[16];
  randomSecureRandom.nextBytes(iv);
  return new EncryptionContext(secretKey,iv);
}
