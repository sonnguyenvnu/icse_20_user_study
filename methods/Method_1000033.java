public byte[] read(int address,int size){
  if (size <= 0) {
    return EMPTY_BYTE_ARRAY;
  }
  extend(address,size);
  byte[] data=new byte[size];
  int chunkIndex=address / CHUNK_SIZE;
  int chunkOffset=address % CHUNK_SIZE;
  int toGrab=data.length;
  int start=0;
  while (toGrab > 0) {
    int copied=grabMax(chunkIndex,chunkOffset,toGrab,data,start);
    ++chunkIndex;
    chunkOffset=0;
    toGrab-=copied;
    start+=copied;
  }
  return data;
}
