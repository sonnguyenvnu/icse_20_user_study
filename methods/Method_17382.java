public boolean contains(long item){
  hashFunc.createHash(item);
  if (!TinySetIndexing.chainExist(chainIndex[hashFunc.fpaux.set],hashFunc.fpaux.chainId)) {
    return false;
  }
  TinySetIndexing.getChain(hashFunc.fpaux,chainIndex,lastIndex);
  int offset=this.itemsPerSet * hashFunc.fpaux.set;
  TinySetIndexing.chainStart+=offset;
  TinySetIndexing.chainEnd+=offset;
  while (TinySetIndexing.chainStart <= TinySetIndexing.chainEnd) {
    try {
      if (cache[TinySetIndexing.chainStart % cache.length] == hashFunc.fpaux.value) {
        return true;
      }
      TinySetIndexing.chainStart++;
    }
 catch (    Exception e) {
      System.out.println(" length: " + cache.length + " Access: " + TinySetIndexing.chainStart);
    }
  }
  return false;
}
