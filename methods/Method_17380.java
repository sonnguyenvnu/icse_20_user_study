public void addItem(long item){
  hashFunc.createHash(item);
  int bucketStart=this.itemsPerSet * hashFunc.fpaux.set;
  if (cache[bucketStart + this.itemsPerSet - 1] != 0) {
    selectVictim(bucketStart);
    return;
  }
  int idxToAdd=TinySetIndexing.addItem(hashFunc.fpaux,chainIndex,lastIndex);
  this.replaceItems(idxToAdd,hashFunc.fpaux.fingerprint,bucketStart,1);
}
