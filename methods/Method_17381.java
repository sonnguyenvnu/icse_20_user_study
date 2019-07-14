private void selectVictim(int bucketStart){
  byte victimOffset=(byte)rnd.nextInt(this.itemsPerSet);
  int victimChain=TinySetIndexing.getChainAtOffset(hashFunc.fpaux,chainIndex,lastIndex,victimOffset);
  if (TinySetIndexing.chainExist(chainIndex[hashFunc.fpaux.set],victimChain)) {
    replace(hashFunc.fpaux,(byte)victimChain,bucketStart,victimOffset);
  }
 else {
    throw new RuntimeException("Failed to replace");
  }
}
