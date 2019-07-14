public long getIndexIdFromKey(StaticBuffer key){
  if (hashKeys)   key=HashingUtil.getKey(hashLength,key);
  return VariableLong.readPositive(key.asReadBuffer());
}
