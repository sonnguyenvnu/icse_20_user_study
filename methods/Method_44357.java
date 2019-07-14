protected <R>R checkResult(HuobiResult<R> huobiResult){
  if (!huobiResult.isSuccess()) {
    String huobiError=huobiResult.getError();
    if (huobiError.length() == 0) {
      throw new ExchangeException("Missing error message");
    }
 else {
      throw new ExchangeException(huobiError);
    }
  }
  return huobiResult.getResult();
}
