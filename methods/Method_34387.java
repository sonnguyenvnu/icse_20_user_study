/** 
 * Fetches the JWK Set from the provided <code>URL</code> and returns a <code>Map</code> keyed by the JWK keyId (&quot;kid&quot;) and mapped to an association of the  {@link JwkDefinition} and {@link SignatureVerifier}. Uses a  {@link JwkSetConverter} to convert the JWK Set URL source to a set of {@link JwkDefinition}(s) followed by the instantiation of a  {@link SignatureVerifier} which is associated to it's {@link JwkDefinition}.
 * @param jwkSetUrl the JWK Set URL
 * @return a <code>Map</code> keyed by the JWK keyId and mapped to an association of {@link JwkDefinition} and {@link SignatureVerifier}
 * @see JwkSetConverter
 */
static Map<String,JwkDefinitionHolder> loadJwkDefinitions(URL jwkSetUrl){
  InputStream jwkSetSource;
  try {
    jwkSetSource=jwkSetUrl.openStream();
  }
 catch (  IOException ex) {
    throw new JwkException("An I/O error occurred while reading from the JWK Set source: " + ex.getMessage(),ex);
  }
  Set<JwkDefinition> jwkDefinitionSet=jwkSetConverter.convert(jwkSetSource);
  Map<String,JwkDefinitionHolder> jwkDefinitions=new LinkedHashMap<String,JwkDefinitionHolder>();
  for (  JwkDefinition jwkDefinition : jwkDefinitionSet) {
    if (JwkDefinition.KeyType.RSA.equals(jwkDefinition.getKeyType())) {
      jwkDefinitions.put(jwkDefinition.getKeyId(),new JwkDefinitionHolder(jwkDefinition,createRsaVerifier((RsaJwkDefinition)jwkDefinition)));
    }
 else     if (JwkDefinition.KeyType.EC.equals(jwkDefinition.getKeyType())) {
      jwkDefinitions.put(jwkDefinition.getKeyId(),new JwkDefinitionHolder(jwkDefinition,createEcVerifier((EllipticCurveJwkDefinition)jwkDefinition)));
    }
  }
  return jwkDefinitions;
}
