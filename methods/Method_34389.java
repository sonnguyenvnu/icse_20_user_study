private static EllipticCurveVerifier createEcVerifier(EllipticCurveJwkDefinition ecDefinition){
  EllipticCurveVerifier result;
  try {
    BigInteger x=new BigInteger(1,Codecs.b64UrlDecode(ecDefinition.getX()));
    BigInteger y=new BigInteger(1,Codecs.b64UrlDecode(ecDefinition.getY()));
    String algorithm=null;
    if (EllipticCurveJwkDefinition.NamedCurve.P256.value().equals(ecDefinition.getCurve())) {
      algorithm=JwkDefinition.CryptoAlgorithm.ES256.standardName();
    }
 else     if (EllipticCurveJwkDefinition.NamedCurve.P384.value().equals(ecDefinition.getCurve())) {
      algorithm=JwkDefinition.CryptoAlgorithm.ES384.standardName();
    }
 else     if (EllipticCurveJwkDefinition.NamedCurve.P521.value().equals(ecDefinition.getCurve())) {
      algorithm=JwkDefinition.CryptoAlgorithm.ES512.standardName();
    }
    result=new EllipticCurveVerifier(x,y,ecDefinition.getCurve(),algorithm);
  }
 catch (  Exception ex) {
    throw new JwkException("An error occurred while creating an EC Public Key Verifier for " + ecDefinition.getKeyId() + " : " + ex.getMessage(),ex);
  }
  return result;
}
