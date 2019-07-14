/** 
 * @param tradableIdentifier
 * @param currency
 * @param orderbookwindow - The width of the Orderbook as a percentage plus and minus the currentprice. Value can be from set: { 2p, 5p, 10p, 20p, 50p, 100p }
 * @return
 */
public BitcoiniumOrderbook getBitcoiniumOrderbook(String tradableIdentifier,String currency,String orderbookwindow) throws IOException {
  String pair=BitcoiniumUtils.createCurrencyPairString(tradableIdentifier,currency);
  verifyPriceWindow(orderbookwindow);
  BitcoiniumOrderbook bitcoiniumDepth=bitcoinium.getDepth(pair,orderbookwindow,exchange.getExchangeSpecification().getApiKey());
  return bitcoiniumDepth;
}
