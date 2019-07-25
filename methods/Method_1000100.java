@Override public DelegatedResourceAccountIndexCapsule get(byte[] key){
  byte[] value=revokingDB.getUnchecked(key);
  return ArrayUtils.isEmpty(value) ? null : new DelegatedResourceAccountIndexCapsule(value);
}
