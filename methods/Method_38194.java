/** 
 * Updates db sql type if not already set.
 */
public void updateDbSqlType(final int dbSqlType){
  if (this.dbSqlType == SqlType.DB_SQLTYPE_UNKNOWN) {
    this.dbSqlType=dbSqlType;
  }
}
