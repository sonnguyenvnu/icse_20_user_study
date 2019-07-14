public boolean addItem(long item){
  hashFunc.createHash(item);
  int bucketStart=this.itemsPerSet * hashFunc.fpaux.set;
  if (cache[bucketStart + this.itemsPerSet - 1] != 0) {
    return selectVictim(bucketStart);
  }
  int idxToAdd=TinySetIndexing.addItem(hashFunc.fpaux,chainIndex,lastIndex);
  this.replaceItems(idxToAdd,hashFunc.fpaux.value,bucketStart,1);
  return false;
}
