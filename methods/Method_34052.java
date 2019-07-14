/** 
 * Convert these random bytes to a verifier string. The length of the byte array can be  {@link #setVerifierLengthBytes(int) configured}. Default implementation mods the bytes to fit into the ASCII letters 1-9, A-Z, a-z .
 * @param verifierBytes The bytes.
 * @return The string.
 */
protected String getVerifierString(byte[] verifierBytes){
  char[] chars=new char[verifierBytes.length];
  for (int i=0; i < verifierBytes.length; i++) {
    chars[i]=DEFAULT_CODEC[((verifierBytes[i] & 0xFF) % DEFAULT_CODEC.length)];
  }
  return new String(chars);
}
