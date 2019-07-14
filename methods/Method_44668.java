public boolean moveFunds(String symbol,BigDecimal amount,AccountType from,AccountType to) throws IOException {
  return okCoin.fundsTransfer(apikey,symbol,amount.toPlainString(),from.getValue(),to.getValue(),signatureCreator()).isResult();
}
