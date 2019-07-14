static RSAPublicKey createPublicKey(BigInteger n,BigInteger e){
  try {
    return (RSAPublicKey)KeyFactory.getInstance("RSA").generatePublic(new RSAPublicKeySpec(n,e));
  }
 catch (  Exception ex) {
    throw new RuntimeException(ex);
  }
}
