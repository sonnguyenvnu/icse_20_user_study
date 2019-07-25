public String mangle(final String username,final String idCode){
  if (null == idCode || idCode.trim().isEmpty()) {
    return "";
  }
  try {
    final MessageDigest md=MessageDigest.getInstance("SHA-256");
    final byte[] plaintext=(salt + username + idCode.trim()).getBytes(Charset.forName("UTF-8"));
    final byte[] digest=md.digest(plaintext);
    final byte[] condensed=new byte[8];
    for (int i=0; i < 8; i++) {
      condensed[i]=(byte)(digest[i] ^ digest[i + 8] ^ digest[i + 16] ^ digest[i + 24]);
    }
    return encoder.encodeToString(condensed).substring(0,11);
  }
 catch (  final NoSuchAlgorithmException e) {
    LOG.error("Unable to mangle ID code.",e);
    return "";
  }
}
