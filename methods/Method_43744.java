private static String md5(String requestString){
  try {
    return DigestUtils.bytesToHex(MessageDigest.getInstance("MD5").digest(requestString.getBytes()));
  }
 catch (  NoSuchAlgorithmException e) {
    throw new RuntimeException(e);
  }
}
