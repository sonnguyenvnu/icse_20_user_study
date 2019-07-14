protected <R>R checkResult(final LiquiResult<R> liquiResult){
  if (liquiResult.getError() != null) {
    throw new LiquiException(liquiResult.getError());
  }
  if (liquiResult.getResult() == null) {
    throw new LiquiException("Result is missing");
  }
  return liquiResult.getResult();
}
