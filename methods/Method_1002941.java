/** 
 * Builds a  {@link SamlAssertionConsumerConfig}.
 */
SamlAssertionConsumerConfig build(){
  return new SamlAssertionConsumerConfig(endpoint,isDefault);
}
