public static List<FundingRecord> adaptWithdrawalRecords(List<BittrexWithdrawalHistory> bittrexFundingHistories){
  final ArrayList<FundingRecord> fundingRecords=new ArrayList<>();
  for (  BittrexWithdrawalHistory f : bittrexFundingHistories) {
    if (f != null) {
      final FundingRecord.Status status=fromWithdrawalRecord(f);
      fundingRecords.add(new FundingRecord(f.getAddress(),f.getOpened(),Currency.getInstance(f.getCurrency()),f.getAmount(),f.getPaymentUuid(),f.getTxId(),FundingRecord.Type.WITHDRAWAL,status,null,f.getTxCost(),null));
    }
  }
  return fundingRecords;
}
