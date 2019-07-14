public BaseYoBitResponse withdrawCoinsToAddress(DefaultWithdrawFundsParams params) throws IOException {
  DefaultWithdrawFundsParams defaultWithdrawFundsParams=params;
  BaseYoBitResponse response=service.withdrawCoinsToAddress(exchange.getExchangeSpecification().getApiKey(),signatureCreator,"WithdrawCoinsToAddress",exchange.getNonceFactory(),defaultWithdrawFundsParams.getCurrency().getCurrencyCode(),defaultWithdrawFundsParams.getAmount(),defaultWithdrawFundsParams.getAddress());
  if (!response.success)   throw new ExchangeException("failed to withdraw funds");
  return response;
}
