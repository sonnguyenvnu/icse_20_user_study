/** 
 * convert luno pair to xchange pair i.e. XBTZAR -> BTC/ZAR we assume, the pair has two currencies with 3 chars length each <- not a very clean approach
 * @param lunoPair
 * @return
 */
public static CurrencyPair fromLunoPair(String lunoPair){
  return new CurrencyPair(fromLunoCurrency(lunoPair.substring(0,3)),fromLunoCurrency(lunoPair.substring(3)));
}
