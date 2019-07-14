private static RSAPrivateKey createPrivateKey(BigInteger n,BigInteger d){
  try {
    return (RSAPrivateKey)KeyFactory.getInstance("RSA").generatePrivate(new RSAPrivateKeySpec(n,d));
  }
 catch (  Exception e) {
    throw new RuntimeException(e);
  }
}
