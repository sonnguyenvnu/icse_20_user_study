private boolean isDuplicateRecord(final SQLException ex){
  return DatabaseType.MySQL.equals(databaseType) && 1062 == ex.getErrorCode() || DatabaseType.H2.equals(databaseType) && 23505 == ex.getErrorCode() || DatabaseType.SQLServer.equals(databaseType) && 1 == ex.getErrorCode() || DatabaseType.DB2.equals(databaseType) && -803 == ex.getErrorCode() || DatabaseType.PostgreSQL.equals(databaseType) && 0 == ex.getErrorCode() || DatabaseType.Oracle.equals(databaseType) && 1 == ex.getErrorCode();
}
