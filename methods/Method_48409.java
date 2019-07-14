void reset(int newOffset,int newLimit){
  assert newOffset >= 0 && newOffset <= newLimit;
  assert newLimit <= array.length;
  this.offset=newOffset;
  this.limit=newLimit;
}
