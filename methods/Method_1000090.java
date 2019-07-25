@Override public boolean has(byte[] key){
  byte[] lowerCaseKey=getLowerCaseAccountId(key);
  byte[] value=revokingDB.getUnchecked(lowerCaseKey);
  return !ArrayUtils.isEmpty(value);
}
