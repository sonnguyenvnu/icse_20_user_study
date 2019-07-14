public CoinbaseProSendMoneyResponse sendMoney(String accountId,String to,BigDecimal amount,Currency currency) throws CoinbaseProException, IOException {
  return coinbasePro.sendMoney(new CoinbaseProSendMoneyRequest(to,amount,currency.getCurrencyCode()),apiKey,digest,nonceFactory,passphrase,accountId);
}
