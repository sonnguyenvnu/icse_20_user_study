protected static <T extends OkCoinErrorResult>T returnOrThrow(T t){
  if (t.isResult()) {
    return t;
  }
 else {
    throw new ExchangeException(OkCoinUtils.getErrorMessage(t.getErrorCode()));
  }
}
