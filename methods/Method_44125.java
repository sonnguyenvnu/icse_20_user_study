protected void checkResult(DSXReturn<?> result){
  String error=result.getError();
  if (!result.isSuccess()) {
    if (error != null) {
      if (error.startsWith(ERR_MSG_NONCE)) {
        throw new NonceException(error);
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
