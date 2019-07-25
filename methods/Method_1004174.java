@Override public void entries(long address,long entries){
  if (entries >= (1L << 32)) {
    throw new IllegalStateException("segment entries overflow: up to " + UNSIGNED_INT_MASK + " supported, " + entries + " given");
  }
  OS.memory().writeInt(address + ENTRIES_OFFSET,(int)entries);
}
