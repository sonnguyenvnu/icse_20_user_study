@Override public long deleted(long address){
  return OS.memory().readInt(address + DELETED_OFFSET) & UNSIGNED_INT_MASK;
}
