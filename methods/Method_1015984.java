@Override public String read(String serviceName,String tableName,String id) throws IOException {
  return getConnector(serviceName).getTable(tableName).read(id);
}
