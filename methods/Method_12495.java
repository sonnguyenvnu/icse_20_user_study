@Override public InstanceId generateId(Registration registration){
  try {
    MessageDigest digest=MessageDigest.getInstance("SHA-1");
    byte[] bytes=digest.digest(registration.getHealthUrl().getBytes(StandardCharsets.UTF_8));
    return InstanceId.of(new String(encodeHex(bytes,0,12)));
  }
 catch (  NoSuchAlgorithmException e) {
    throw new IllegalStateException(e);
  }
}
