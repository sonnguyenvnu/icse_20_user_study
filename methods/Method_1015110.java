private Range sibling(Range r){
  long size=r.end - r.start;
  long start=r.start ^ size;
  return new Range(start,start + size);
}
