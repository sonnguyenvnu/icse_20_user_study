/** 
 * Creates a  {@link RsaJwkDefinition} based on the supplied attributes.
 * @param attributes the attributes used to create the {@link RsaJwkDefinition}
 * @return a {@link JwkDefinition} representation of a RSA Key
 * @throws JwkException if at least one attribute value is missing or invalid for a RSA Key
 */
private JwkDefinition createRsaJwkDefinition(Map<String,String> attributes){
  String keyId=attributes.get(KEY_ID);
  if (!StringUtils.hasText(keyId)) {
    throw new JwkException(KEY_ID + " is a required attribute for a JWK.");
  }
  JwkDefinition.PublicKeyUse publicKeyUse=JwkDefinition.PublicKeyUse.fromValue(attributes.get(PUBLIC_KEY_USE));
  if (!JwkDefinition.PublicKeyUse.SIG.equals(publicKeyUse)) {
    throw new JwkException((publicKeyUse != null ? publicKeyUse.value() : "unknown") + " (" + PUBLIC_KEY_USE + ") is currently not supported.");
  }
  JwkDefinition.CryptoAlgorithm algorithm=JwkDefinition.CryptoAlgorithm.fromHeaderParamValue(attributes.get(ALGORITHM));
  if (algorithm != null && !JwkDefinition.CryptoAlgorithm.RS256.equals(algorithm) && !JwkDefinition.CryptoAlgorithm.RS384.equals(algorithm) && !JwkDefinition.CryptoAlgorithm.RS512.equals(algorithm)) {
    throw new JwkException(algorithm.standardName() + " (" + ALGORITHM + ") is currently not supported.");
  }
  String modulus=attributes.get(RSA_PUBLIC_KEY_MODULUS);
  if (!StringUtils.hasText(modulus)) {
    throw new JwkException(RSA_PUBLIC_KEY_MODULUS + " is a required attribute for a RSA JWK.");
  }
  String exponent=attributes.get(RSA_PUBLIC_KEY_EXPONENT);
  if (!StringUtils.hasText(exponent)) {
    throw new JwkException(RSA_PUBLIC_KEY_EXPONENT + " is a required attribute for a RSA JWK.");
  }
  RsaJwkDefinition jwkDefinition=new RsaJwkDefinition(keyId,publicKeyUse,algorithm,modulus,exponent);
  return jwkDefinition;
}
