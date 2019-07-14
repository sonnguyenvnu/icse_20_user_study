@Override public List<ModifiedRecord> deleteSqlPreviousData(Connection connection,DeleteImageParams deleteImageParams) throws SQLException {
  String beforeSql=SqlUtils.SELECT + String.join(SqlUtils.SQL_COMMA_SEPARATOR,deleteImageParams.getColumns()) + SqlUtils.FROM + String.join(SqlUtils.SQL_COMMA_SEPARATOR,deleteImageParams.getTables()) + SqlUtils.WHERE + deleteImageParams.getSqlWhere();
  return queryRunner.query(connection,beforeSql,new TxcModifiedRecordListHandler(deleteImageParams.getPrimaryKeys(),deleteImageParams.getColumns()));
}
