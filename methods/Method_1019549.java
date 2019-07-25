@Override public ArrayOfDoublesSketchIterator iterator(){
  return new DirectArrayOfDoublesSketchIterator(mem_,keysOffset_,getCurrentCapacity(),numValues_);
}
