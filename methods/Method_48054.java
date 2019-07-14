@Override public Map<String,String> getCompressionOptions(String cf) throws BackendException {
  try {
    Keyspace k=keyspaceContext.getClient();
    KeyspaceDefinition keyspaceDefinition=k.describeKeyspace();
    if (null == keyspaceDefinition) {
      throw new PermanentBackendException("Keyspace " + k.getKeyspaceName() + " is undefined");
    }
    ColumnFamilyDefinition columnFamilyDefinition=keyspaceDefinition.getColumnFamily(cf);
    if (null == columnFamilyDefinition) {
      throw new PermanentBackendException("Column family " + cf + " is undefined");
    }
    return columnFamilyDefinition.getCompressionOptions();
  }
 catch (  ConnectionException e) {
    throw new PermanentBackendException(e);
  }
}
