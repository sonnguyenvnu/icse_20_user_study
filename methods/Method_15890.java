private void closeTransaction(Transaction tx){
  if (tx != null) {
    try {
      tx.close();
    }
 catch (    SQLException ignore) {
    }
  }
}
