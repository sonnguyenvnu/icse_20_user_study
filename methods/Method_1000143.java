public boolean contain(Sha256Hash hash,MessageTypes type){
  if (type.equals(MessageTypes.BLOCK)) {
    return dbManager.containBlock(hash);
  }
 else   if (type.equals(MessageTypes.TRX)) {
    return dbManager.getTransactionStore().has(hash.getBytes());
  }
  return false;
}
