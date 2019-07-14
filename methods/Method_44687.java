/** 
 * ????????
 * @param symbol
 * @param type ???? 0:?????? 1:???????
 * @param orderIds
 * @return
 */
public OkCoinOrderResult getOrder(String symbol,Integer type,String orderIds) throws IOException {
  OkCoinOrderResult orderResult=okCoin.getOrders(apikey,type,orderIds,symbol,signatureCreator());
  return returnOrThrow(orderResult);
}
