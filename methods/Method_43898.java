/** 
 * Factory
 * @param commonCode commonly used code for this currency: "BTC"
 * @param name Name of the currency: "Bitcoin"
 * @param unicode Unicode symbol for the currency: "\u20BF" or "?"
 * @param alternativeCodes Alternative codes for the currency: "XBT"
 */
private static Currency createCurrency(String commonCode,String name,String unicode,String... alternativeCodes){
  CurrencyAttributes attributes=new CurrencyAttributes(commonCode,name,unicode,alternativeCodes);
  Currency currency=new Currency(commonCode,attributes);
  for (  String code : attributes.codes) {
    if (commonCode.equals(code)) {
      currencies.put(code,currency);
    }
 else     if (!currencies.containsKey(code)) {
      currencies.put(code,new Currency(code,attributes));
    }
  }
  return currency;
}
