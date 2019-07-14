private static FundingRecord.Status fromWithdrawalRecord(BittrexWithdrawalHistory bittrexWithdrawal){
  if (bittrexWithdrawal.getCanceled())   return FundingRecord.Status.CANCELLED;
  if (bittrexWithdrawal.getInvalidAddress())   return FundingRecord.Status.FAILED;
  if (bittrexWithdrawal.getPendingPayment())   return FundingRecord.Status.PROCESSING;
  if (bittrexWithdrawal.getAuthorized())   return FundingRecord.Status.COMPLETE;
  return FundingRecord.Status.FAILED;
}
