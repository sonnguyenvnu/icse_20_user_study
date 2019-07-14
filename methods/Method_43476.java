/** 
 * Returns the public key of the API key.
 * @return the public key of the API key.
 */
public String getKey(){
  return exchange.getExchangeSpecification().getApiKey();
}
