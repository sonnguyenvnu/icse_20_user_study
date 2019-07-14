private FundingRecord.Status convert(DSXTransHistoryResult.Status status){
switch (status) {
case Completed:
    return FundingRecord.Status.COMPLETE;
case Failed:
  return FundingRecord.Status.FAILED;
case Processing:
return FundingRecord.Status.PROCESSING;
case Rejected:
return FundingRecord.Status.CANCELLED;
default :
throw new RuntimeException("Unknown DSX transaction status: " + status);
}
}
