public String hash(final String value){
  final MessageDigest messageDigest=messageDigestThreadLocal.get();
  messageDigest.reset();
  messageDigest.update(salt);
  messageDigest.update(value.getBytes(Charsets.UTF_8));
  return Hex.encodeHexString(messageDigest.digest());
}
