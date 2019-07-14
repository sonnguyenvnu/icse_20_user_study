/** 
 * Ends the current basic block. This method must be used in the case where the current basic block does not have any successor. <p>WARNING: this method must be called after the currently visited instruction has been put in {@link #code} (if frames are computed, this method inserts a new Label to start a new basicblock after the current instruction).
 */
private void endCurrentBasicBlockWithNoSuccessor(){
  if (compute == COMPUTE_ALL_FRAMES) {
    Label nextBasicBlock=new Label();
    nextBasicBlock.frame=new Frame(nextBasicBlock);
    nextBasicBlock.resolve(code.data,code.length);
    lastBasicBlock.nextBasicBlock=nextBasicBlock;
    lastBasicBlock=nextBasicBlock;
    currentBasicBlock=null;
  }
 else   if (compute == COMPUTE_MAX_STACK_AND_LOCAL) {
    currentBasicBlock.outputStackMax=(short)maxRelativeStackSize;
    currentBasicBlock=null;
  }
}
