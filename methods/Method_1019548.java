@Override public ArrayOfDoublesSketchIterator iterator(){
  return new DirectArrayOfDoublesSketchIterator(mem_,ENTRIES_START,getRetainedEntries(),numValues_);
}
