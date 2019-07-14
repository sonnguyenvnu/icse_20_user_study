public Object getGeneratedKeyObject(){
  checkInitialized();
  final ResultSet rs=getGeneratedColumns();
  try {
    return DbUtil.getFirstObject(rs);
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
