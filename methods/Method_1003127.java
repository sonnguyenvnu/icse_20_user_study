@Override public void add(Session session,Row row){
  TransactionMap<Value,Value> map=getMap(session);
  ValueArray array=convertToKey(row,null);
  boolean checkRequired=indexType.isUnique() && !mayHaveNullDuplicates(row);
  if (checkRequired) {
    checkUnique(map,array,Long.MIN_VALUE);
  }
  try {
    map.put(array,ValueNull.INSTANCE);
  }
 catch (  IllegalStateException e) {
    throw mvTable.convertException(e);
  }
  if (checkRequired) {
    checkUnique(map,array,row.getKey());
  }
}
