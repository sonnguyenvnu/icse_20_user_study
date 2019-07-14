@Override public void deleteByFields(List<Field> fields) throws TxLoggerException {
  if (Objects.isNull(dbHelper)) {
    throw new TxLoggerException("???????");
  }
  StringBuilder sql=new StringBuilder("delete from t_logger where 1=1 and ");
  List<String> values=whereSqlAppender(sql,fields);
  dbHelper.update(sql.toString(),values.toArray(new Object[0]));
}
