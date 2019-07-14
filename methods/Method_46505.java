@Override public void resolveUpdateImage(UpdateImageParams updateImageParams) throws TxcLogicException {
  List<ModifiedRecord> modifiedRecords;
  Connection connection=(Connection)DTXLocalContext.cur().getResource();
  try {
    modifiedRecords=txcSqlExecutor.updateSqlPreviousData(connection,updateImageParams);
  }
 catch (  SQLException e) {
    throw new TxcLogicException(e);
  }
  resolveModifiedRecords(modifiedRecords,SqlUtils.SQL_TYPE_UPDATE);
}
