/** 
 * Corresponds to <code>DELETE orders/{order-id}</delete>
 * @return
 * @throws IOException
 */
public String deleteAbucoinsOrder(String orderID) throws IOException {
  String resp=abucoinsAuthenticated.deleteOrder(orderID,exchange.getExchangeSpecification().getApiKey(),signatureCreator,exchange.getExchangeSpecification().getPassword(),timestamp());
  String[] ids=AbucoinsAdapters.adaptToSetOfIDs(resp);
  return ids[0];
}
