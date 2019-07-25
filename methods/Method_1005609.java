/** 
 * Does the conversion.
 */
private void doit(){
  int[] workSet=Bits.makeBitSet(maxLabel);
  Bits.set(workSet,0);
  addSetupBlocks();
  setFirstFrame();
  for (; ; ) {
    int offset=Bits.findFirst(workSet,0);
    if (offset < 0) {
      break;
    }
    Bits.clear(workSet,offset);
    ByteBlock block=blocks.labelToBlock(offset);
    Frame frame=startFrames[offset];
    try {
      processBlock(block,frame,workSet);
    }
 catch (    SimException ex) {
      ex.addContext("...while working on block " + Hex.u2(offset));
      throw ex;
    }
  }
  addReturnBlock();
  addSynchExceptionHandlerBlock();
  addExceptionSetupBlocks();
  if (hasSubroutines) {
    inlineSubroutines();
  }
}
