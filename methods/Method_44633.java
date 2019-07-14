/** 
 * Return something like <code>btc_brl:83948239</code> 
 */
public static String makeMercadoBitcoinOrderId(CurrencyPair currencyPair,String orderId){
  String pair;
  if (currencyPair.equals(CurrencyPair.BTC_BRL)) {
    pair="btc_brl";
  }
 else   if (currencyPair.equals(new CurrencyPair(Currency.LTC,Currency.BRL))) {
    pair="ltc_brl";
  }
 else {
    throw new NotAvailableFromExchangeException();
  }
  return pair + ":" + orderId;
}
