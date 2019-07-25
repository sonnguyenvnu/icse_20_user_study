@Override public long entries(long address){
  return OS.memory().readInt(address + ENTRIES_OFFSET) & UNSIGNED_INT_MASK;
}
