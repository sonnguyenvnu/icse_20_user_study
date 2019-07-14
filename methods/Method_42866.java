/** 
 * Corresponds to <code>DELETE /orders</code> or <code>DELETE orders?product_id={product-id}</delete>
 * @return
 * @throws IOException
 */
public String[] deleteAllAbucoinsOrders(String... productIDs) throws IOException {
  String res;
  if (productIDs.length == 0)   return AbucoinsAdapters.adaptToSetOfIDs(abucoinsAuthenticated.deleteAllOrders(exchange.getExchangeSpecification().getApiKey(),signatureCreator,exchange.getExchangeSpecification().getPassword(),timestamp()));
 else {
    List<String> ids=new ArrayList<>();
    for (    String productID : productIDs) {
      res=abucoinsAuthenticated.deleteAllOrdersForProduct(productID,exchange.getExchangeSpecification().getApiKey(),signatureCreator,exchange.getExchangeSpecification().getPassword(),timestamp());
      String[] deletedIds=AbucoinsAdapters.adaptToSetOfIDs(res);
      for (      String id : deletedIds)       ids.add(id);
    }
    return ids.toArray(new String[ids.size()]);
  }
}
