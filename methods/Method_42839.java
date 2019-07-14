public static List<FundingRecord> adaptFundingRecords(AbucoinsWithdrawalsHistory history){
  List<FundingRecord> retVal=new ArrayList<>();
  for (  AbucoinsWithdrawalHistory h : history.getHistory())   retVal.add(adaptFundingRecord(h));
  return retVal;
}
