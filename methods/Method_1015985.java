@Override public Iterator<String> ids(String serviceName,String tableName) throws IOException {
  return getConnector(serviceName).getTable(tableName).iterator();
}
