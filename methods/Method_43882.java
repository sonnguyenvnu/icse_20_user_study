/** 
 * marketDepth
 * @return Object
 * @throws IOException
 */
public CoinsuperResponse<CoinsuperOrderbook> marketDepth(Map<String,String> parameters) throws IOException {
  RestRequestParam restRequestParam=RestApiRequestHandler.generateRequestParam(parameters,this.apiKey,this.secretKey);
  return coinsuper.marketDepth(restRequestParam);
}
