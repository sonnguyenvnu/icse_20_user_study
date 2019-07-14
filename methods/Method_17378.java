public int countItem(long item){
  hashFunc.createHash(item);
  int $=0;
  if (!TinySetIndexing.chainExist(chainIndex[hashFunc.fpaux.set],hashFunc.fpaux.chainId)) {
    return 0;
  }
  TinySetIndexing.getChain(hashFunc.fpaux,chainIndex,lastIndex);
  int offset=this.itemsPerSet * hashFunc.fpaux.set;
  TinySetIndexing.chainStart+=offset;
  TinySetIndexing.chainEnd+=offset;
  while (TinySetIndexing.chainStart <= TinySetIndexing.chainEnd) {
    try {
      $+=(cache[TinySetIndexing.chainStart % cache.length] == hashFunc.fpaux.fingerprint) ? 1 : 0;
      TinySetIndexing.chainStart++;
    }
 catch (    Exception e) {
      System.out.println(" length: " + cache.length + " Access: " + TinySetIndexing.chainStart);
    }
  }
  return $;
}
