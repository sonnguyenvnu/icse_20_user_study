public long withdrawFiat(String currency,BigDecimal amount) throws IOException {
  DSXFiatWithdrawReturn info=dsx.fiatWithdraw(apiKey,signatureCreator,exchange.getNonceFactory(),currency,amount);
  checkResult(info);
  return info.getReturnValue().getTransactionId();
}
