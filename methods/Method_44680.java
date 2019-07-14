/** 
 * @param symbol btc_usdt,ltc_usdt,eth_usdt ?
 * @return
 * @throws IOException
 */
public OkCoinFutureComment getFuturesIndex(String symbol) throws IOException {
  return okCoin.getFuturesIndex("1",symbol);
}
