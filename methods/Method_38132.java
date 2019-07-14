/** 
 * Closes all result sets opened by this query. Query remains active. Returns <code>SQLException</code> (stacked with all exceptions) or <code>null</code>.
 */
private SQLException closeQueryResultSets(){
  SQLException sqlException=null;
  if (resultSets != null) {
    for (    final ResultSet rs : resultSets) {
      try {
        rs.close();
      }
 catch (      SQLException sex) {
        if (sqlException == null) {
          sqlException=sex;
        }
 else {
          sqlException.setNextException(sex);
        }
      }
 finally {
        totalOpenResultSetCount--;
      }
    }
    resultSets.clear();
    resultSets=null;
  }
  return sqlException;
}
