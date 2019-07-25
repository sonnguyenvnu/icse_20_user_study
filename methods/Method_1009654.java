@Override public void open(int taskNumber,int numTasks) throws IOException {
  if (connector != null)   return;
  connector=new KuduConnector(kuduMasters,tableInfo,consistency,writeMode,FlushMode.AUTO_FLUSH_SYNC);
  serializer=serializer.withSchema(tableInfo.getSchema());
}
