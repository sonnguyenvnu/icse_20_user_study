/** 
 * ????
 * @param symbol
 * @param type ???(buy/sell)
 * @param ordersData "[{price:3,amount:5,type:'sell'},{price:3,amount:3,type:'buy'}]"???????orders_data ?type ????orders_data???type ????type????? ????type?????orderData ????type
 * @return
 * @throws IOException
 */
public OkCoinMoreTradeResult batchTrade(String symbol,String type,String ordersData) throws IOException {
  OkCoinMoreTradeResult tradeResult=okCoin.batchTrade(apikey,symbol,type,ordersData,signatureCreator());
  return returnOrThrow(tradeResult);
}
