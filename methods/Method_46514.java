@Override public List<ModifiedRecord> selectSqlPreviousPrimaryKeys(Connection connection,SelectImageParams selectImageParams) throws SQLException {
  return queryRunner.query(connection,selectImageParams.getSql(),new TxcModifiedRecordListHandler(selectImageParams.getPrimaryKeys(),selectImageParams.getPrimaryKeys()));
}
