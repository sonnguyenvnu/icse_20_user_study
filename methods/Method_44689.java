/** 
 * ??OKEX???????????
 * @param symbol
 * @param since
 * @param date
 * @return
 * @throws IOException
 */
public OkCoinFuturesTradeHistoryResult[] getFuturesTradesHistory(String symbol,long since,String date) throws IOException {
  OkCoinFuturesTradeHistoryResult[] futuresHistory=okCoin.getFuturesTradeHistory(apikey,since,symbol,date,signatureCreator());
  return (futuresHistory);
}
