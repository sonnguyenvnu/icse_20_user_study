public static FundingRecord adaptTransaction(BTCTurkUserTransactions transaction){
  String description=transaction.getOperation().toString();
  if (transaction.getId() != null) {
    description+=", index: " + transaction.getId();
  }
  return new FundingRecord.Builder().setInternalId(transaction.getId().toString()).setDate(transaction.getDate()).setType(transaction.getOperation().getType()).setCurrency(transaction.getCurrency()).setAmount(transaction.getAmount()).setFee(transaction.getFee()).setBalance(transaction.getFunds()).setDescription(description).build();
}
