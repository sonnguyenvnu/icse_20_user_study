private void cleanupUserTransaction(){
  if (ut != null) {
    UserTransactionHelper.returnUserTransaction(ut);
    ut=null;
  }
}
