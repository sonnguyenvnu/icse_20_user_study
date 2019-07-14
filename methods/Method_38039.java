@Override public synchronized void init(){
  try {
    Class.forName(driverClass);
  }
 catch (  ClassNotFoundException cnfex) {
    throw new DbSqlException("JDBC driver not found: " + driverClass,cnfex);
  }
}
