public HuobiFundingRecord[] getDepositWithdrawalHistory(String currency,String type,String from) throws IOException {
  HuobiFundingHistoryResult fundingHistoryResult=huobi.getFundingHistory(currency.toLowerCase(),type.toLowerCase(),from,"100",exchange.getExchangeSpecification().getApiKey(),HuobiDigest.HMAC_SHA_256,2,HuobiUtils.createUTCDate(exchange.getNonceFactory()),signatureCreator);
  return checkResult(fundingHistoryResult);
}
