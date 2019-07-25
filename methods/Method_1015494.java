protected SecretKey hash(byte[] key) throws Exception {
  MessageDigest digest=MessageDigest.getInstance("SHA-256");
  digest.update(key);
  byte[] hashed_key=digest.digest();
  return new SecretKeySpec(hashed_key,0,secret_key_length / 8,secret_key_algorithm);
}
