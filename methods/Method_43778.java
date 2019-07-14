public static CoinEggDigest createInstance(String privateKey){
  try {
    CoinEggDigest.UTF8=Charset.forName("UTF-8");
    MessageDigest md5=MessageDigest.getInstance("MD5");
    return new CoinEggDigest(hex(md5.digest(privateKey.getBytes(UTF8))));
  }
 catch (  NoSuchAlgorithmException e) {
    throw new RuntimeException("Illegal algorithm for post body digest. Check the implementation.");
  }
}
