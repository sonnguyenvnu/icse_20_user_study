public static FundingRecord adaptDeposit(BiboxDeposit d){
  return new FundingRecord(d.to,d.getCreatedAt(),Currency.getInstance(d.coinSymbol),d.amount,null,null,Type.DEPOSIT,convertStatus(d.status),null,null,null);
}
