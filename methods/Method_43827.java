/** 
 * Parse errors from HTTP exceptions 
 */
public static void adapt(HttpStatusIOException httpStatusException){
  String msg="HTTP Status: " + httpStatusException.getHttpStatusCode();
  if (isNotEmpty(httpStatusException.getHttpBody())) {
    ObjectMapper mapper=new ObjectMapper();
    CmcResult result;
    try {
      result=mapper.readValue(httpStatusException.getHttpBody(),CmcResult.class);
    }
 catch (    Exception e) {
      throw new ExchangeException(msg,httpStatusException);
    }
    if (result.getStatus() != null && isNotEmpty(result.getStatus().getErrorMessage()) && !result.isSuccess()) {
      String error=result.getStatus().getErrorMessage();
      if (result.getStatus().getErrorCode() == 401) {
        throw new ExchangeSecurityException(error);
      }
      if (result.getStatus().getErrorCode() == 402) {
        throw new FundsExceededException(error);
      }
      if (result.getStatus().getErrorCode() == 429) {
        throw new FrequencyLimitExceededException(error);
      }
      msg=error + " - ErrorCode: " + result.getStatus().getErrorCode();
      throw new ExchangeException(msg);
    }
  }
  throw new ExchangeException(msg,httpStatusException);
}
