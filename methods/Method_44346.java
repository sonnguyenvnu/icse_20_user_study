public static List<FundingRecord> adaptFundingHistory(HuobiFundingRecord[] fundingRecords){
  List<FundingRecord> records=new ArrayList<>();
  for (  HuobiFundingRecord record : fundingRecords) {
    records.add(adaptFundingRecord(record));
  }
  return records;
}
