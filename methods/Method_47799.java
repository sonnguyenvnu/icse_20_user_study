@Override public synchronized void beginTransaction(){
  try {
    connection.setAutoCommit(false);
    transactionSuccessful=false;
  }
 catch (  SQLException e) {
    throw new RuntimeException(e);
  }
}
