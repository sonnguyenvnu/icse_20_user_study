private static RsaVerifier createRsaVerifier(RsaJwkDefinition rsaDefinition){
  RsaVerifier result;
  try {
    BigInteger modulus=new BigInteger(1,Codecs.b64UrlDecode(rsaDefinition.getModulus()));
    BigInteger exponent=new BigInteger(1,Codecs.b64UrlDecode(rsaDefinition.getExponent()));
    RSAPublicKey rsaPublicKey=(RSAPublicKey)KeyFactory.getInstance("RSA").generatePublic(new RSAPublicKeySpec(modulus,exponent));
    if (rsaDefinition.getAlgorithm() != null) {
      result=new RsaVerifier(rsaPublicKey,rsaDefinition.getAlgorithm().standardName());
    }
 else {
      result=new RsaVerifier(rsaPublicKey);
    }
  }
 catch (  Exception ex) {
    throw new JwkException("An error occurred while creating a RSA Public Key Verifier for " + rsaDefinition.getKeyId() + " : " + ex.getMessage(),ex);
  }
  return result;
}
