/** 
 * Modify or cancel/replace an existing limit order
 * @implNote Some exchanges have API methods that allow to modify an order or cancel an existingone and create a new one in one request. <p>Based on exchange API there are 3 ways, how this function works: <ol> <li>Exchange supports existing order modify operation. Then function returns  {@code limitOrder} order ID.<li>Exchange supports order cancel/replace by one request. Then function returns new order ID. <li>Exchange doesn't support any of these operations. Then function performs cancel/replace by two separate requests, and returns new order ID (default behavior) </ol>
 * @param limitOrder Order's data to change
 * @return Order ID
 * @throws ExchangeException Indication that the exchange reported some kind of error with therequest or response
 * @throws NotAvailableFromExchangeException Indication that the exchange does not support therequested function or data
 * @throws NotYetImplementedForExchangeException Indication that the exchange supports therequested function or data, but it has not yet been implemented
 * @throws IOException Indication that a networking error occurred while fetching JSON data
 */
default String changeOrder(LimitOrder limitOrder) throws IOException {
  cancelOrder(limitOrder.getId());
  return placeLimitOrder(limitOrder);
}
