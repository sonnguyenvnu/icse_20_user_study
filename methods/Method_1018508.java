public int read(String s,Pointer pointer,int pointerOffset,@size_t long size,@off_t long offset,FuseFileInfo fuseFileInfo){
  if (DEBUG)   System.out.printf("read(%s, offset=%d, size=%d)\n",s,offset,size);
  if (!containedInOneChunk(offset,offset + size)) {
    long boundary=alignToChunkSize(offset + Chunk.MAX_SIZE);
    int r1=read(s,pointer,0,boundary - offset,offset,fuseFileInfo);
    if (r1 <= 0)     return r1;
    int r2=read(s,pointer,(int)(boundary - offset),size + offset - boundary,boundary,fuseFileInfo);
    if (r2 <= 0)     return r2;
    return r1 + r2;
  }
  long startPos=alignToChunkSize(offset);
  int chunkOffset=intraChunkOffset(offset);
  int iSize=(int)size;
  CacheEntryHolder cacheEntryHolder=entryMap.computeIfAbsent(s,path -> new CacheEntryHolder(new CacheEntry(path,startPos)));
  return cacheEntryHolder.apply(c -> c != null && c.offset == startPos,() -> new CacheEntry(s,startPos),ce -> ce.read(pointer,pointerOffset,chunkOffset,iSize));
}
