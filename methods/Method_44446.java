@Override public String withdrawFunds(Currency currency,BigDecimal amount,String address) throws IOException {
  return withdraw(null,currency.toString(),address,amount).getRefid();
}
