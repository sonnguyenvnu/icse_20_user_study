/** 
 * @return Object
 * @throws IOException
 */
public CoinsuperResponse<List<CoinsuperPair>> getSymbolList(Map<String,String> data) throws IOException {
  RestRequestParam parameters=RestApiRequestHandler.generateRequestParam(data,this.apiKey,this.secretKey);
  return coinsuper.getSymbolList(parameters);
}
