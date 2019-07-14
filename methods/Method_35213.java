private List<RouterTransaction> getVisibleTransactions(@NonNull Iterator<RouterTransaction> backstackIterator){
  List<RouterTransaction> transactions=new ArrayList<>();
  while (backstackIterator.hasNext()) {
    RouterTransaction transaction=backstackIterator.next();
    transactions.add(transaction);
    if (transaction.pushChangeHandler() == null || transaction.pushChangeHandler().removesFromViewOnPush()) {
      break;
    }
  }
  Collections.reverse(transactions);
  return transactions;
}
