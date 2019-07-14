/** 
 * Returns the JWK definition matching the provided keyId (&quot;kid&quot;).
 * @param keyId the Key ID (&quot;kid&quot;)
 * @return the matching {@link JwkDefinition} or null if not found
 */
private JwkDefinitionHolder getDefinition(String keyId){
  return this.jwkDefinitions.get(keyId);
}
