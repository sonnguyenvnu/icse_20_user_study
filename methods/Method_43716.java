public String moveFunds(Currency currency,String address,BigDecimal amount) throws IOException {
  org.knowm.xchange.coinbasepro.dto.account.CoinbaseProAccount[] accounts=getCoinbaseProAccountInfo();
  String accountId=null;
  for (  org.knowm.xchange.coinbasepro.dto.account.CoinbaseProAccount account : accounts) {
    if (currency.getCurrencyCode().equals(account.getCurrency())) {
      accountId=account.getId();
    }
  }
  if (accountId == null) {
    throw new ExchangeException("Cannot determine account id for currency " + currency.getCurrencyCode());
  }
  CoinbaseProSendMoneyResponse response=sendMoney(accountId,address,amount,currency);
  if (response.getData() != null) {
    return response.getData().getId();
  }
  return null;
}
