@Override public List<ModifiedRecord> updateSqlPreviousData(Connection connection,UpdateImageParams updateImageParams) throws SQLException {
  String beforeSql=SqlUtils.SELECT + String.join(SqlUtils.SQL_COMMA_SEPARATOR,updateImageParams.getColumns()) + SqlUtils.SQL_COMMA_SEPARATOR + String.join(SqlUtils.SQL_COMMA_SEPARATOR,updateImageParams.getPrimaryKeys()) + SqlUtils.FROM + String.join(SqlUtils.SQL_COMMA_SEPARATOR,updateImageParams.getTables()) + SqlUtils.WHERE + updateImageParams.getWhereSql();
  return queryRunner.query(connection,beforeSql,new TxcModifiedRecordListHandler(updateImageParams.getPrimaryKeys(),updateImageParams.getColumns()));
}
