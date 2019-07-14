/** 
 * Corresponds to <code>GET /fills</code>
 * @return
 * @throws IOException
 */
public AbucoinsFills getAbucoinsFills(String afterCursor,Integer limit) throws IOException {
  try {
    AbucoinsFills fills=abucoinsAuthenticated.getFills(exchange.getExchangeSpecification().getApiKey(),signatureCreator,exchange.getExchangeSpecification().getPassword(),timestamp(),afterCursor,limit);
    return fills;
  }
 catch (  HttpStatusIOException initialException) {
    try {
      AbucoinsError error=ObjectMapperHelper.readValue(initialException.getHttpBody(),AbucoinsError.class);
      throw new ExchangeException(error.getMessage());
    }
 catch (    IOException finalException) {
      throw new ExportException("Response neither expected DTO nor error message",finalException);
    }
  }
}
