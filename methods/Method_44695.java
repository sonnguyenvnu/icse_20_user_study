public BigDecimal getStaticFee(String currency){
  CurrencyMetaData cmd=this.exchange.getExchangeMetaData().getCurrencies().get(Currency.getInstance(currency));
  if (cmd == null || cmd.getWithdrawalFee() == null) {
    throw new IllegalArgumentException("Unsupported withdraw currency " + currency);
  }
  return cmd.getWithdrawalFee();
}
