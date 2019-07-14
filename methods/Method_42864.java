/** 
 * Corresponds to <code>GET orders/{order-id}</delete> or <code>GET orders?status={status}</code> or <code>orders?product_id={product-id}</code> or <code>orders?status={status}&product_id={product-id}</code>.
 * @return
 * @throws IOException
 */
public AbucoinsOrder[] getAbucoinsOrders(AbucoinsOrderRequest request) throws IOException {
  AbucoinsOrder.Status status=null;
  String productID=null;
  if (request != null) {
    status=request.getStatus();
    productID=request.getProductID();
  }
  if (status != null) {
switch (status) {
default :
case open:
case done:
      break;
case pending:
case rejected:
    throw new IllegalArgumentException("/orders only accepts status of 'open' or 'done' not " + status);
}
}
AbucoinsOrders retVal=null;
if (status == null) {
if (productID == null) retVal=abucoinsAuthenticated.getOrders(exchange.getExchangeSpecification().getApiKey(),signatureCreator,exchange.getExchangeSpecification().getPassword(),timestamp());
 else retVal=abucoinsAuthenticated.getOrdersByProductID(productID,exchange.getExchangeSpecification().getApiKey(),signatureCreator,exchange.getExchangeSpecification().getPassword(),timestamp());
}
 else {
if (productID == null) retVal=abucoinsAuthenticated.getOrdersByStatus(status.name(),exchange.getExchangeSpecification().getApiKey(),signatureCreator,exchange.getExchangeSpecification().getPassword(),timestamp());
 else retVal=abucoinsAuthenticated.getOrdersByStatusAndProductID(status.name(),productID,exchange.getExchangeSpecification().getApiKey(),signatureCreator,exchange.getExchangeSpecification().getPassword(),timestamp());
}
if (retVal.getOrders().length == 1 && retVal.getOrders()[0].getMessage() != null) throw new ExchangeException(retVal.getOrders()[0].getMessage());
return retVal.getOrders();
}
