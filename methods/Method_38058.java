/** 
 * Detects database and configure DbOom engine.
 */
public DbServer detectDatabaseAndConfigureDbOom(final ConnectionProvider cp,final DbOomConfig dbOomConfig){
  cp.init();
  final Connection connection=cp.getConnection();
  final DbServer dbServer=detectDatabase(connection);
  cp.closeConnection(connection);
  dbServer.accept(dbOomConfig);
  return dbServer;
}
