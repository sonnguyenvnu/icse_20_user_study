public static StatementInfo insert(TableRecord tableRecord){
  StringBuilder rollbackSql=new StringBuilder(SqlUtils.DELETE).append(SqlUtils.FROM).append(tableRecord.getTableName()).append(SqlUtils.WHERE);
  Object[] paramArray=new Object[tableRecord.getFieldCluster().getPrimaryKeys().size()];
  int j=whereBuilder(tableRecord.getFieldCluster().getPrimaryKeys(),rollbackSql,paramArray,-1);
  SqlUtils.cutSuffix(SqlUtils.AND,rollbackSql);
  return new StatementInfo(rollbackSql.toString(),Arrays.copyOf(paramArray,j));
}
