/** 
 * Get order info from Wex exchange.
 * @param orderId The ID of the order to display
 * @return Order info.
 */
public WexOrderInfoResult getBTCEOrderInfo(Long orderId) throws IOException {
  WexOrderInfoReturn btceOrderInfo=btce.OrderInfo(apiKey,signatureCreator,exchange.getNonceFactory(),orderId);
  checkResult(btceOrderInfo);
  return btceOrderInfo.getReturnValue().values().iterator().next();
}
