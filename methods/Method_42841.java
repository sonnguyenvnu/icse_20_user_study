public static FundingRecord.Status adaptFundingStatus(AbucoinsHistory.Status abucoinsStatus){
switch (abucoinsStatus) {
case unknown:
default :
case awaitingEmailConfirmation:
case pending:
    return FundingRecord.Status.PROCESSING;
case sent:
case complete:
case completed:
  return FundingRecord.Status.COMPLETE;
}
}
