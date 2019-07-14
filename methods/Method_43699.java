public static FundingRecord adaptFundingRecord(Currency currency,CoinbaseProTransfer coinbaseProTransfer){
  FundingRecord.Status status=FundingRecord.Status.PROCESSING;
  Date processedAt=coinbaseProTransfer.processedAt();
  Date canceledAt=coinbaseProTransfer.canceledAt();
  if (canceledAt != null)   status=FundingRecord.Status.CANCELLED;
 else   if (processedAt != null)   status=FundingRecord.Status.COMPLETE;
  Date timestamp=coinbaseProTransfer.createdAt();
  String address=coinbaseProTransfer.getDetails().getCryptoAddress();
  if (address == null)   address=coinbaseProTransfer.getDetails().getSentToAddress();
  return new FundingRecord(address,timestamp,currency,coinbaseProTransfer.amount(),coinbaseProTransfer.getId(),coinbaseProTransfer.getDetails().getCryptoTransactionHash(),coinbaseProTransfer.type(),status,null,null,null);
}
