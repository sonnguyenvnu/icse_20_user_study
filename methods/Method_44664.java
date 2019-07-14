public static FundingRecord adaptFundingRecord(OkexDepositRecord r){
  return new FundingRecord.Builder().setAddress(r.getTo()).setAmount(r.getAmount()).setCurrency(Currency.getInstance(r.getCurrency())).setDate(r.getTimestamp()).setStatus(convertDepositStatus(r.getStatus())).setBlockchainTransactionHash(r.getTxid()).setType(Type.DEPOSIT).build();
}
