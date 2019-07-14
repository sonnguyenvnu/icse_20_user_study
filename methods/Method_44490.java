public static FundingRecord adaptFundingRecord(DepositResponse dr){
  FundingRecord.Builder b=new FundingRecord.Builder();
  return b.setAddress(dr.getAddress()).setAmount(dr.getAmount()).setCurrency(Currency.getInstance(dr.getCurrency())).setFee(dr.getFee()).setType(Type.DEPOSIT).setStatus(convertStatus(dr.getStatus())).setBlockchainTransactionHash(dr.getWalletTxId()).setDescription(dr.getMemo()).setDate(dr.getCreatedAt()).build();
}
