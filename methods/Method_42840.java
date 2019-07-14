static FundingRecord.Builder fundingRecordBuilder(AbucoinsHistory history){
  return new FundingRecord.Builder().setDescription(history.getUrl()).setAmount(history.getAmount()).setCurrency(Currency.getInstance(history.getCurrency())).setDate(parseDate(history.getDate())).setFee(history.getFee()).setStatus(adaptFundingStatus(history.getStatus()));
}
