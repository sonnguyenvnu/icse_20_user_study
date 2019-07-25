@Override public boolean has(byte[] key){
  byte[] value=revokingDB.getUnchecked(key);
  return !ArrayUtils.isEmpty(value);
}
