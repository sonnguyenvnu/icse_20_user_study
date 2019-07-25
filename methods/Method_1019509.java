@Override public HashIterator iterator(){
  return new MemoryHashIterator(mem_,getRetainedEntries(),getThetaLong());
}
