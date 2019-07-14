/** 
 * {@inheritDoc}
 */
@Override public synchronized void init(){
  if (initialised) {
    return;
  }
  if (log.isInfoEnabled()) {
    log.info("Core connection pool initialization");
  }
  try {
    Class.forName(driver);
  }
 catch (  ClassNotFoundException cnfex) {
    throw new DbSqlException("Database driver not found: " + driver,cnfex);
  }
  if (minConnections > maxConnections) {
    minConnections=maxConnections;
  }
  availableConnections=new ArrayList<>(maxConnections);
  busyConnections=new ArrayList<>(maxConnections);
  for (int i=0; i < minConnections; i++) {
    try {
      Connection conn=DriverManager.getConnection(url,user,password);
      availableConnections.add(new ConnectionData(conn));
    }
 catch (    SQLException sex) {
      throw new DbSqlException("No database connection",sex);
    }
  }
  initialised=true;
}
