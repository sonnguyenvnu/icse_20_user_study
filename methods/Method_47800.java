@Override public synchronized void endTransaction(){
  try {
    if (transactionSuccessful)     connection.commit();
 else     connection.rollback();
    connection.setAutoCommit(true);
  }
 catch (  SQLException e) {
    throw new RuntimeException(e);
  }
}
