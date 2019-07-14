public HitbtcInternalTransferResponse transferFunds(Currency currency,BigDecimal amount,HitbtcTransferType hitbtcTransferType) throws IOException {
  return hitbtc.transferToTrading(amount,currency.getCurrencyCode(),hitbtcTransferType.getType());
}
