public String transferToMain(Currency currency,BigDecimal amount) throws IOException {
  HitbtcInternalTransferResponse response=transferFunds(currency,amount,HitbtcTransferType.EXCHANGE_TO_BANK);
  if (response.id == null) {
    throw new ExchangeException("transfer failed: " + response);
  }
  return response.id;
}
