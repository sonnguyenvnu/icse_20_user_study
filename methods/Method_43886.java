/** 
 * @return Object
 * @throws IOException
 */
public CoinsuperResponse<List<String>> orderOpenList(Map<String,String> parameters) throws IOException {
  RestRequestParam restParameters=RestApiRequestHandler.generateRequestParam(parameters,this.apiKey,this.secretKey);
  return coinsuper.orderOpenList(restParameters);
}
