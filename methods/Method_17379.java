/** 
 * Implementing add and remove together in one function, means that less items are shifted. (reduction of 3 times from trivial implementation).
 */
private int replace(HashedItem fpaux,byte victim,int bucketStart,int removedOffset){
  byte chainId=fpaux.chainId;
  fpaux.chainId=victim;
  this.cache[bucketStart + removedOffset]=0;
  TinySetIndexing.removeItem(fpaux,chainIndex,lastIndex);
  fpaux.chainId=chainId;
  int idxToAdd=TinySetIndexing.addItem(fpaux,chainIndex,lastIndex);
  int delta=(removedOffset < idxToAdd) ? -1 : 1;
  replaceItems(idxToAdd,fpaux.fingerprint,bucketStart,delta);
  return removedOffset;
}
