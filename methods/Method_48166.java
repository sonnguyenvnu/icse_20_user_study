public Map<String,Throwable> commitIndexes(){
  final Map<String,Throwable> exceptions=new HashMap<>(indexTx.size());
  for (  Map.Entry<String,IndexTransaction> indexTransactionEntry : indexTx.entrySet()) {
    try {
      indexTransactionEntry.getValue().commit();
    }
 catch (    Throwable e) {
      exceptions.put(indexTransactionEntry.getKey(),e);
    }
  }
  return exceptions;
}
