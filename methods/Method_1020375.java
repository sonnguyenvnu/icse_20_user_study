/** 
 * Runs algorithm. TODO: This is n^2, and could be made linear-ish with a hash. In particular, hash the contents of each block and only compare blocks with the same hash.
 * @return {@code non-null;} new method that has been processed
 */
public RopMethod process(){
  int szBlocks=blocks.size();
  BitSet toDelete=new BitSet(blocks.getMaxLabel());
  for (int bindex=0; bindex < szBlocks; bindex++) {
    BasicBlock b=blocks.get(bindex);
    if (toDelete.get(b.getLabel())) {
      continue;
    }
    IntList preds=ropMethod.labelToPredecessors(b.getLabel());
    int szPreds=preds.size();
    for (int i=0; i < szPreds; i++) {
      int iLabel=preds.get(i);
      BasicBlock iBlock=blocks.labelToBlock(iLabel);
      if (toDelete.get(iLabel) || iBlock.getSuccessors().size() > 1 || iBlock.getFirstInsn().getOpcode().getOpcode() == RegOps.MOVE_RESULT) {
        continue;
      }
      IntList toCombine=new IntList();
      for (int j=i + 1; j < szPreds; j++) {
        int jLabel=preds.get(j);
        BasicBlock jBlock=blocks.labelToBlock(jLabel);
        if (jBlock.getSuccessors().size() == 1 && compareInsns(iBlock,jBlock)) {
          toCombine.add(jLabel);
          toDelete.set(jLabel);
        }
      }
      combineBlocks(iLabel,toCombine);
    }
  }
  for (int i=szBlocks - 1; i >= 0; i--) {
    if (toDelete.get(newBlocks.get(i).getLabel())) {
      newBlocks.set(i,null);
    }
  }
  newBlocks.shrinkToFit();
  newBlocks.setImmutable();
  return new RopMethod(newBlocks,ropMethod.getFirstLabel());
}
