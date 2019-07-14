@Override public void resolveDeleteImage(DeleteImageParams deleteImageParams) throws TxcLogicException {
  List<ModifiedRecord> modifiedRecords;
  Connection connection=(Connection)DTXLocalContext.cur().getResource();
  try {
    modifiedRecords=txcSqlExecutor.deleteSqlPreviousData(connection,deleteImageParams);
  }
 catch (  SQLException e) {
    throw new TxcLogicException(e);
  }
  resolveModifiedRecords(modifiedRecords,SqlUtils.SQL_TYPE_DELETE);
}
