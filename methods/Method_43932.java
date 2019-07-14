/** 
 * Get an item from the arbitrary exchange-specific parameters to be passed to the exchange implementation.
 * @return a Map of named exchange-specific parameter values
 */
public Object getExchangeSpecificParametersItem(String key){
  return exchangeSpecificParameters.get(key);
}
