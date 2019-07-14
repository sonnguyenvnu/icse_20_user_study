public static FundingRecord adaptDeposit(BiboxWithdrawal w){
  return new FundingRecord(w.toAddress,w.getCreatedAt(),Currency.getInstance(w.coinSymbol),w.amountReal,null,null,Type.WITHDRAWAL,convertStatus(w.status),null,null,null);
}
