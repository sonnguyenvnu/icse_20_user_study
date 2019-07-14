/** 
 * Returns generated key i.e. first generated column as <code>long</code>.
 */
public long getGeneratedKey(){
  checkInitialized();
  final ResultSet rs=getGeneratedColumns();
  try {
    return DbUtil.getFirstLong(rs);
  }
 catch (  SQLException sex) {
    throw new DbSqlException(this,"No generated key as long",sex);
  }
 finally {
    DbUtil.close(rs);
    resultSets.remove(rs);
    totalOpenResultSetCount--;
  }
}
