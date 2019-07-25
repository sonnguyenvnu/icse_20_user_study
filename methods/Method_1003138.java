private int rewrite(Page p,Set<Integer> set){
  if (p.isLeaf()) {
    long pos=p.getPos();
    int chunkId=DataUtils.getPageChunkId(pos);
    if (!set.contains(chunkId)) {
      return 0;
    }
    assert p.getKeyCount() > 0;
    return rewritePage(p) ? 1 : 0;
  }
  int writtenPageCount=0;
  for (int i=0; i < getChildPageCount(p); i++) {
    long childPos=p.getChildPagePos(i);
    if (childPos != 0 && DataUtils.getPageType(childPos) == DataUtils.PAGE_TYPE_LEAF) {
      int chunkId=DataUtils.getPageChunkId(childPos);
      if (!set.contains(chunkId)) {
        continue;
      }
    }
    writtenPageCount+=rewrite(p.getChildPage(i),set);
  }
  if (writtenPageCount == 0) {
    long pos=p.getPos();
    int chunkId=DataUtils.getPageChunkId(pos);
    if (set.contains(chunkId)) {
      while (!p.isLeaf()) {
        p=p.getChildPage(0);
      }
      if (rewritePage(p)) {
        writtenPageCount=1;
      }
    }
  }
  return writtenPageCount;
}
