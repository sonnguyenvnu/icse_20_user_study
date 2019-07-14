public static List<FundingRecord> adaptUserTransactionsToFundingRecords(Bl3pUserTransactions.Bl3pUserTransaction[] transactions){
  List<FundingRecord> list=new ArrayList<>(transactions.length);
  for (  Bl3pUserTransactions.Bl3pUserTransaction tx : transactions) {
    list.add(new FundingRecord.Builder().setAmount(tx.amount.value).setBalance(tx.balance.value).setCurrency(Currency.getInstance(tx.amount.currency)).setDate(tx.date).setFee(tx.fee == null ? null : tx.fee.value).setType(tx.type == "deposit" ? FundingRecord.Type.DEPOSIT : FundingRecord.Type.WITHDRAWAL).build());
  }
  return list;
}
