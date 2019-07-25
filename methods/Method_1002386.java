public int hash(String[] keyTokens){
  byte[] digest=getMD5Digest(keyTokens);
  return ((0xff & digest[12]) << 24) | ((0xff & digest[13]) << 16) | ((0xff & digest[14]) << 8) | (0xff & digest[15]);
}
