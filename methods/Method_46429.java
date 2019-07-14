@Override public LogList findByLimitAndFields(int page,int limit,int timeOrder,List<Field> list) throws TxLoggerException {
  if (Objects.isNull(dbHelper)) {
    throw new TxLoggerException("???????");
  }
  StringBuilder countSql=new StringBuilder("select count(*) from t_logger where 1=1 and ");
  StringBuilder sql=new StringBuilder("select * from t_logger where 1=1 and ");
  List<String> values=whereSqlAppender(sql,list);
  whereSqlAppender(countSql,list);
  Object[] params=values.toArray(new Object[0]);
  long total=dbHelper.query(countSql.toString(),new ScalarHandler<>(),params);
  if (total < (page - 1) * limit) {
    page=1;
  }
  sql.append(timeOrderSql(timeOrder)).append(" limit ").append((page - 1) * limit).append(", ").append(limit);
  List<TxLog> txLogs=dbHelper.query(sql.toString(),new BeanListHandler<>(TxLog.class,processor),params);
  LogList logList=new LogList();
  logList.setTotal(total);
  logList.setTxLogs(txLogs);
  return logList;
}
