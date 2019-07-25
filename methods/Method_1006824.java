@Override public boolean refresh(T obj){
  Parsed parsed=Parser.get(this.clz);
  Field keyField=parsed.getKeyField(X.KEY_ONE);
  if (Objects.isNull(keyField))   throw new RuntimeException("No PrimaryKey, UnSafe Refresh, try to invoke DefaultRepository.refreshUnSafe(RefreshCondition<T> refreshCondition)");
  keyField.setAccessible(true);
  try {
    Object value=keyField.get(obj);
    if (Objects.isNull(value) || value.toString().equals("0"))     throw new RuntimeException("UnSafe Refresh, try to invoke DefaultRepository.refreshUnSafe(RefreshCondition<T> refreshCondition)");
  }
 catch (  Exception e) {
    e.printStackTrace();
    throw new RuntimeException("refresh safe, get keyOne exception");
  }
  return dataRepository.refresh(obj);
}
