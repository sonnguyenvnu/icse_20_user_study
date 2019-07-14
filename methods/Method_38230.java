/** 
 * Resolves column db sql type and populates it in column descriptor if missing.
 */
protected void resolveColumnDbSqlType(final Connection connection,final DbEntityColumnDescriptor dec){
  if (dec.dbSqlType != SqlType.DB_SQLTYPE_UNKNOWN) {
    return;
  }
  ResultSet rs=null;
  DbEntityDescriptor ded=dec.getDbEntityDescriptor();
  try {
    DatabaseMetaData dmd=connection.getMetaData();
    rs=dmd.getColumns(null,ded.getSchemaName(),ded.getTableName(),dec.getColumnName());
    if (rs.next()) {
      dec.dbSqlType=rs.getInt("DATA_TYPE");
    }
 else {
      dec.dbSqlType=SqlType.DB_SQLTYPE_NOT_AVAILABLE;
      if (log.isWarnEnabled()) {
        log.warn("Column SQL type not available: " + ded.toString() + '.' + dec.getColumnName());
      }
    }
  }
 catch (  SQLException sex) {
    dec.dbSqlType=SqlType.DB_SQLTYPE_NOT_AVAILABLE;
    if (log.isWarnEnabled()) {
      log.warn("Column SQL type not resolved: " + ded.toString() + '.' + dec.getColumnName(),sex);
    }
  }
 finally {
    DbUtil.close(rs);
  }
}
