private void free(ChainLoopDetector loopDetector){
  int nextBlock=startBlock;
  while (nextBlock != POIFSConstants.END_OF_CHAIN) {
    int thisBlock=nextBlock;
    loopDetector.claim(thisBlock);
    nextBlock=blockStore.getNextBlock(thisBlock);
    blockStore.setNextBlock(thisBlock,POIFSConstants.UNUSED_BLOCK);
  }
  this.startBlock=POIFSConstants.END_OF_CHAIN;
}
