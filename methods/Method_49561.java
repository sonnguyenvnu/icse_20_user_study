@Override public HTableDescriptor newTableDescriptor(String tableName){
  TableName tn=TableName.valueOf(tableName);
  return new HTableDescriptor(tn);
}
