/** 
 * Get Map of order history from DSX exchange. All parameters are nullable
 * @param count Number of orders to display. Default value is 1000
 * @param fromId ID of the first order of the selection
 * @param endId ID of the last order of the selection
 * @param order Order in which transactions shown. Possible values: «asc» — from first to last,«desc» — from last to first. Default value is «desc»
 * @param since Time from which start selecting orders by trade time(UNIX time). If this value isnot null order will become «asc»
 * @param end Time to which start selecting orders by trade time(UNIX time). If this value is notnull order will become «asc»
 * @param pair Currency pair
 * @return Map of order history
 * @throws IOException
 */
public Map<Long,DSXOrderHistoryResult> getDSXOrderHistory(Long count,Long fromId,Long endId,DSXAuthenticatedV2.SortOrder order,Long since,Long end,String pair) throws IOException {
  DSXOrderHistoryReturn dsxOrderHistory=dsx.orderHistory(apiKey,signatureCreator,exchange.getNonceFactory(),count,fromId,endId,order,since,end,pair);
  String error=dsxOrderHistory.getError();
  if (MSG_NO_TRADES.equals(error)) {
    return Collections.emptyMap();
  }
  checkResult(dsxOrderHistory);
  return dsxOrderHistory.getReturnValue();
}
