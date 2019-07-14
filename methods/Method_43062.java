/** 
 * Creates a valid currency pair for Bitcoinium.com
 * @param tradableIdentifier
 * @param currency
 * @return
 */
public static String createCurrencyPairString(String tradableIdentifier,String currency){
  return tradableIdentifier + "_" + currency;
}
