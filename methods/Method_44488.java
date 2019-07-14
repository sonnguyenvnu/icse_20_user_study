public static FundingRecord adaptFundingRecord(WithdrawalResponse wr){
  FundingRecord.Builder b=new FundingRecord.Builder();
  return b.setAddress(wr.getAddress()).setAmount(wr.getAmount()).setCurrency(Currency.getInstance(wr.getCurrency())).setFee(wr.getFee()).setType(Type.WITHDRAWAL).setStatus(convertStatus(wr.getStatus())).setInternalId(wr.getId()).setBlockchainTransactionHash(wr.getWalletTxId()).setDescription(wr.getMemo()).setDate(wr.getCreatedAt()).build();
}
