public static List<FundingRecord> adaptFundingHistory(Map<String,KrakenLedger> krakenLedgerInfo){
  final List<FundingRecord> fundingRecords=new ArrayList<>();
  for (  Entry<String,KrakenLedger> ledgerEntry : krakenLedgerInfo.entrySet()) {
    final KrakenLedger krakenLedger=ledgerEntry.getValue();
    if (krakenLedger.getLedgerType() != null) {
      final Currency currency=adaptCurrency(krakenLedger.getAsset());
      if (currency != null) {
        final Date timestamp=new Date((long)(krakenLedger.getUnixTime() * 1000L));
        final FundingRecord.Type type=FundingRecord.Type.fromString(krakenLedger.getLedgerType().name());
        if (type != null) {
          final String internalId=krakenLedger.getRefId();
          FundingRecord fundingRecordEntry=new FundingRecord(null,timestamp,currency,krakenLedger.getTransactionAmount(),internalId,null,FundingRecord.Type.fromString(krakenLedger.getLedgerType().name()),FundingRecord.Status.COMPLETE,krakenLedger.getBalance(),krakenLedger.getFee(),null);
          fundingRecords.add(fundingRecordEntry);
        }
      }
    }
  }
  return fundingRecords;
}
