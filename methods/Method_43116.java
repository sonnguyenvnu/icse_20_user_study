public BitfinexDepositWithdrawalHistoryResponse[] getDepositWithdrawalHistory(String currency,String method,Date since,Date until,Integer limit) throws IOException {
  BitfinexDepositWithdrawalHistoryRequest request=new BitfinexDepositWithdrawalHistoryRequest(String.valueOf(exchange.getNonceFactory().createValue()),currency,method,since,until,limit);
  return bitfinex.depositWithdrawalHistory(apiKey,payloadCreator,signatureCreator,request);
}
