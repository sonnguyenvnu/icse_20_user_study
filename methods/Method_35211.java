private void ensureOrderedTransactionIndices(List<RouterTransaction> backstack){
  List<Integer> indices=new ArrayList<>(backstack.size());
  for (  RouterTransaction transaction : backstack) {
    transaction.ensureValidIndex(getTransactionIndexer());
    indices.add(transaction.transactionIndex);
  }
  Collections.sort(indices);
  for (int i=0; i < backstack.size(); i++) {
    backstack.get(i).transactionIndex=indices.get(i);
  }
}
