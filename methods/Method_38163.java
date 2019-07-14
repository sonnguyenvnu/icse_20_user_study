/** 
 * Opens a transaction.
 */
protected void openTx(){
  if (connection == null) {
    connection=connectionProvider.getConnection();
  }
  txActive=true;
  try {
    connection.setAutoCommit(false);
    if (txMode.getIsolation() != DbTransactionMode.ISOLATION_DEFAULT) {
      connection.setTransactionIsolation(txMode.getIsolation());
    }
    connection.setReadOnly(txMode.isReadOnly());
  }
 catch (  SQLException sex) {
    throw new DbSqlException("Open TX failed",sex);
  }
}
