/** 
 * can be “bitcoin”, “litecoin” or “ethereum” or “tether” or “wire”.
 * @param currency
 * @return
 */
public static String convertToGeminiCcyName(String currency){
  if (currency.toUpperCase().equals("BTC"))   return "btc";
  if (currency.toUpperCase().equals("ETH"))   return "eth";
  throw new GeminiException("Cannot determine withdrawal type.");
}
