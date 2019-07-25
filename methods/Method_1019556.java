@Override public ArrayOfDoublesSketchIterator iterator(){
  return new HeapArrayOfDoublesSketchIterator(keys_,values_,numValues_);
}
