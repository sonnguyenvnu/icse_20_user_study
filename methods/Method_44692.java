/** 
 * @param symbol
 * @param contractType
 * @param ordersData
 * @param leverRate
 * @throws IOException
 */
public OkCoinMoreTradeResult futureBatchTrade(String symbol,String contractType,String ordersData,String leverRate) throws IOException {
  return okCoin.futureBatchTrade(apikey,symbol,contractType,ordersData,signatureCreator(),leverRate);
}
