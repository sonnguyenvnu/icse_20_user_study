public static List<FundingRecord> adaptFundingHistory(List<CryptonitUserTransaction> userTransactions){
  List<FundingRecord> fundingRecords=new ArrayList<>();
  for (  CryptonitUserTransaction trans : userTransactions) {
    if (trans.isDeposit() || trans.isWithdrawal()) {
      FundingRecord.Type type=trans.isDeposit() ? FundingRecord.Type.DEPOSIT : FundingRecord.Type.WITHDRAWAL;
      Map.Entry<String,BigDecimal> amount=CryptonitAdapters.findNonzeroAmount(trans);
      FundingRecord record=new FundingRecord(null,trans.getDatetime(),Currency.getInstance(amount.getKey()),amount.getValue().abs(),String.valueOf(trans.getId()),null,type,FundingRecord.Status.COMPLETE,null,trans.getFee(),null);
      fundingRecords.add(record);
    }
  }
  return fundingRecords;
}
