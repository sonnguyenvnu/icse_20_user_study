public static List<FundingRecord> adaptDepositRecords(List<BittrexDepositHistory> bittrexFundingHistories){
  final ArrayList<FundingRecord> fundingRecords=new ArrayList<>();
  for (  BittrexDepositHistory f : bittrexFundingHistories) {
    if (f != null) {
      fundingRecords.add(new FundingRecord(f.getCryptoAddress(),f.getLastUpdated(),Currency.getInstance(f.getCurrency()),f.getAmount(),String.valueOf(f.getId()),f.getTxId(),FundingRecord.Type.DEPOSIT,FundingRecord.Status.COMPLETE,null,null,null));
    }
  }
  return fundingRecords;
}
