/** 
 * @param pair
 * @return
 */
public static String toSymbol(CurrencyPair pair){
  if (pair.equals(CurrencyPair.IOTA_BTC)) {
    return "IOTABTC";
  }
  return pair.base.getCurrencyCode() + pair.counter.getCurrencyCode();
}
