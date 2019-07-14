private HTableDescriptor ensureTableExists(String tableName,String initialCFName,int ttlInSeconds) throws BackendException {
  AdminMask adm=null;
  HTableDescriptor desc;
  try {
    adm=getAdminInterface();
    if (adm.tableExists(tableName)) {
      desc=adm.getTableDescriptor(tableName);
      if (shortCfNames && initialCFName.equals(shortCfNameMap.get(SYSTEM_PROPERTIES_STORE_NAME))) {
        String longCFName=shortCfNameMap.inverse().get(initialCFName);
        if (desc.getFamily(Bytes.toBytes(longCFName)) != null) {
          logger.warn("Configuration {}=true, but the table \"{}\" already has column family with long name \"{}\".",SHORT_CF_NAMES.getName(),tableName,longCFName);
          logger.warn("Check {} configuration.",SHORT_CF_NAMES.getName());
        }
      }
 else       if (!shortCfNames && initialCFName.equals(SYSTEM_PROPERTIES_STORE_NAME)) {
        String shortCFName=shortCfNameMap.get(initialCFName);
        if (desc.getFamily(Bytes.toBytes(shortCFName)) != null) {
          logger.warn("Configuration {}=false, but the table \"{}\" already has column family with short name \"{}\".",SHORT_CF_NAMES.getName(),tableName,shortCFName);
          logger.warn("Check {} configuration.",SHORT_CF_NAMES.getName());
        }
      }
    }
 else {
      desc=createTable(tableName,initialCFName,ttlInSeconds,adm);
    }
  }
 catch (  IOException e) {
    throw new TemporaryBackendException(e);
  }
 finally {
    IOUtils.closeQuietly(adm);
  }
  return desc;
}
