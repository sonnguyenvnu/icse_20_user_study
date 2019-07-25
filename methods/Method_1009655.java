@Override public void open(Configuration parameters) throws IOException {
  if (this.connector != null)   return;
  this.connector=new KuduConnector(kuduMasters,tableInfo,consistency,writeMode,getflushMode());
  this.serializer.withSchema(tableInfo.getSchema());
}
