private HTableDescriptor createTable(String tableName,String cfName,int ttlInSeconds,AdminMask adm) throws IOException {
  HTableDescriptor desc=compat.newTableDescriptor(tableName);
  HColumnDescriptor columnDescriptor=new HColumnDescriptor(cfName);
  setCFOptions(columnDescriptor,ttlInSeconds);
  compat.addColumnFamilyToTableDescriptor(desc,columnDescriptor);
  int count;
  String src;
  if (MIN_REGION_COUNT <= (count=regionCount)) {
    src="region count configuration";
  }
 else   if (0 < regionsPerServer && MIN_REGION_COUNT <= (count=regionsPerServer * adm.getEstimatedRegionServerCount())) {
    src="ClusterStatus server count";
  }
 else {
    count=-1;
    src="default";
  }
  if (MIN_REGION_COUNT < count) {
    adm.createTable(desc,getStartKey(count),getEndKey(count),count);
    logger.debug("Created table {} with region count {} from {}",tableName,count,src);
  }
 else {
    adm.createTable(desc);
    logger.debug("Created table {} with default start key, end key, and region count",tableName);
  }
  return desc;
}
