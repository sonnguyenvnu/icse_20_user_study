private void ensureColumnFamilyExists(String tableName,String columnFamily,int ttlInSeconds) throws BackendException {
  AdminMask adm=null;
  try {
    adm=getAdminInterface();
    HTableDescriptor desc=ensureTableExists(tableName,columnFamily,ttlInSeconds);
    Preconditions.checkNotNull(desc);
    HColumnDescriptor cf=desc.getFamily(Bytes.toBytes(columnFamily));
    if (cf == null) {
      try {
        if (!adm.isTableDisabled(tableName)) {
          adm.disableTable(tableName);
        }
      }
 catch (      TableNotEnabledException e) {
        logger.debug("Table {} already disabled",tableName);
      }
catch (      IOException e) {
        throw new TemporaryBackendException(e);
      }
      try {
        HColumnDescriptor columnDescriptor=new HColumnDescriptor(columnFamily);
        setCFOptions(columnDescriptor,ttlInSeconds);
        adm.addColumn(tableName,columnDescriptor);
        try {
          logger.debug("Added HBase ColumnFamily {}, waiting for 1 sec. to propogate.",columnFamily);
          Thread.sleep(1000L);
        }
 catch (        InterruptedException ie) {
          throw new TemporaryBackendException(ie);
        }
        adm.enableTable(tableName);
      }
 catch (      TableNotFoundException ee) {
        logger.error("TableNotFoundException",ee);
        throw new PermanentBackendException(ee);
      }
catch (      org.apache.hadoop.hbase.TableExistsException ee) {
        logger.debug("Swallowing exception {}",ee);
      }
catch (      IOException ee) {
        throw new TemporaryBackendException(ee);
      }
    }
  }
  finally {
    IOUtils.closeQuietly(adm);
  }
}
