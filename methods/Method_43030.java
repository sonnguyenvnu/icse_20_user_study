private static FundingRecord adaptFundingRecord(Map item){
  FundingRecord.Type type=item.get("type").toString().equalsIgnoreCase("WITHDRAWAL_SUBTRACT_FUNDS") ? FundingRecord.Type.WITHDRAWAL : FundingRecord.Type.DEPOSIT;
  return new FundingRecord.Builder().setType(type).setBlockchainTransactionHash(null).setAddress(null).setAmount(new BigDecimal(item.get("value").toString()).abs()).setCurrency(Currency.getInstance(((Map)item.get("balance")).get("currency").toString())).setDate(DateUtils.fromMillisUtc(Long.valueOf(item.get("time").toString()))).setInternalId(item.get("historyId").toString()).setFee(null).setStatus(FundingRecord.Status.COMPLETE).setBalance(new BigDecimal(((Map)item.get("fundsAfter")).get("total").toString())).build();
}
