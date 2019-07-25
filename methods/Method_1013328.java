public long sizeof(){
  long size=44;
  size+=maxTblCnt * LongSize;
  size+=getIndexCapacity() * 4;
  return size;
}
