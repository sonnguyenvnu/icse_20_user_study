public static FundingRecord adaptFundingRecord(OkexWithdrawalRecord r){
  return new FundingRecord.Builder().setAddress(r.getTo()).setAmount(r.getAmount()).setCurrency(Currency.getInstance(r.getCurrency())).setDate(r.getTimestamp()).setInternalId(r.getWithdrawalId()).setStatus(convertWithdrawalStatus(r.getStatus())).setBlockchainTransactionHash(r.getTxid()).setType(Type.WITHDRAWAL).build();
}
