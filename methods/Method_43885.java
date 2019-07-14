/** 
 * @return Object
 * @throws IOException
 */
public CoinsuperResponse<List<OrderList>> orderList(Map<String,String> parameters) throws IOException {
  RestRequestParam restParameters=RestApiRequestHandler.generateRequestParam(parameters,this.apiKey,this.secretKey);
  return coinsuper.orderList(restParameters);
}
