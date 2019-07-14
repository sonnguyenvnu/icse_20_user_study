/** 
 * Returns generated columns.
 */
public ResultSet getGeneratedColumns(){
  checkInitialized();
  if (generatedColumns == null) {
    throw new DbSqlException(this,"No column is specified as auto-generated");
  }
  final ResultSet rs;
  try {
    rs=statement.getGeneratedKeys();
  }
 catch (  SQLException sex) {
    throw new DbSqlException(this,"No generated keys",sex);
  }
  saveResultSet(rs);
  totalOpenResultSetCount++;
  return rs;
}
