public String withdrawFundsRaw(Currency currency,BigDecimal amount,String address,String paymentId,Boolean includeFee) throws HttpStatusIOException {
  Map response=hitbtc.payout(amount,currency.getCurrencyCode(),address,paymentId,includeFee);
  return response.get("id").toString();
}
