/** 
 * Creates an  {@link EllipticCurveJwkDefinition} based on the supplied attributes.
 * @param attributes the attributes used to create the {@link EllipticCurveJwkDefinition}
 * @return a {@link JwkDefinition} representation of an EC Key
 * @throws JwkException if at least one attribute value is missing or invalid for an EC Key
 */
private JwkDefinition createEllipticCurveJwkDefinition(Map<String,String> attributes){
  String keyId=attributes.get(KEY_ID);
  if (!StringUtils.hasText(keyId)) {
    throw new JwkException(KEY_ID + " is a required attribute for an EC JWK.");
  }
  JwkDefinition.PublicKeyUse publicKeyUse=JwkDefinition.PublicKeyUse.fromValue(attributes.get(PUBLIC_KEY_USE));
  if (!JwkDefinition.PublicKeyUse.SIG.equals(publicKeyUse)) {
    throw new JwkException((publicKeyUse != null ? publicKeyUse.value() : "unknown") + " (" + PUBLIC_KEY_USE + ") is currently not supported.");
  }
  JwkDefinition.CryptoAlgorithm algorithm=JwkDefinition.CryptoAlgorithm.fromHeaderParamValue(attributes.get(ALGORITHM));
  if (algorithm != null && !JwkDefinition.CryptoAlgorithm.ES256.equals(algorithm) && !JwkDefinition.CryptoAlgorithm.ES384.equals(algorithm) && !JwkDefinition.CryptoAlgorithm.ES512.equals(algorithm)) {
    throw new JwkException(algorithm.standardName() + " (" + ALGORITHM + ") is currently not supported.");
  }
  String x=attributes.get(EC_PUBLIC_KEY_X);
  if (!StringUtils.hasText(x)) {
    throw new JwkException(EC_PUBLIC_KEY_X + " is a required attribute for an EC JWK.");
  }
  String y=attributes.get(EC_PUBLIC_KEY_Y);
  if (!StringUtils.hasText(y)) {
    throw new JwkException(EC_PUBLIC_KEY_Y + " is a required attribute for an EC JWK.");
  }
  String curve=attributes.get(EC_PUBLIC_KEY_CURVE);
  if (!StringUtils.hasText(curve)) {
    throw new JwkException(EC_PUBLIC_KEY_CURVE + " is a required attribute for an EC JWK.");
  }
  EllipticCurveJwkDefinition jwkDefinition=new EllipticCurveJwkDefinition(keyId,publicKeyUse,algorithm,x,y,curve);
  return jwkDefinition;
}
