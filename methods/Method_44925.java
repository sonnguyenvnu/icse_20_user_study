protected void checkResult(WexReturn<?> result){
  String error=result.getError();
  if (!result.isSuccess()) {
    if (error != null) {
      if (error.startsWith(ERR_MSG_NONCE)) {
        throw new NonceException(error);
      }
 else       if (error.startsWith(ERR_MSG_FUNDS)) {
        throw new FundsExceededException(error);
      }
 else       if (error.equals("no transactions")) {
        return;
      }
    }
    throw new ExchangeException(error);
  }
 else   if (result.getReturnValue() == null) {
    throw new ExchangeException("Didn't receive any return value. Message: " + error);
  }
 else   if (error != null) {
    throw new ExchangeException(error);
  }
}
