/** 
 * Computes all the stack map frames of the method, from scratch. 
 */
private void computeAllFrames(){
  Handler handler=firstHandler;
  while (handler != null) {
    String catchTypeDescriptor=handler.catchTypeDescriptor == null ? "java/lang/Throwable" : handler.catchTypeDescriptor;
    int catchType=Frame.getAbstractTypeFromInternalName(symbolTable,catchTypeDescriptor);
    Label handlerBlock=handler.handlerPc.getCanonicalInstance();
    handlerBlock.flags|=Label.FLAG_JUMP_TARGET;
    Label handlerRangeBlock=handler.startPc.getCanonicalInstance();
    Label handlerRangeEnd=handler.endPc.getCanonicalInstance();
    while (handlerRangeBlock != handlerRangeEnd) {
      handlerRangeBlock.outgoingEdges=new Edge(catchType,handlerBlock,handlerRangeBlock.outgoingEdges);
      handlerRangeBlock=handlerRangeBlock.nextBasicBlock;
    }
    handler=handler.nextHandler;
  }
  Frame firstFrame=firstBasicBlock.frame;
  firstFrame.setInputFrameFromDescriptor(symbolTable,accessFlags,descriptor,this.maxLocals);
  firstFrame.accept(this);
  Label listOfBlocksToProcess=firstBasicBlock;
  listOfBlocksToProcess.nextListElement=Label.EMPTY_LIST;
  int maxStackSize=0;
  while (listOfBlocksToProcess != Label.EMPTY_LIST) {
    Label basicBlock=listOfBlocksToProcess;
    listOfBlocksToProcess=listOfBlocksToProcess.nextListElement;
    basicBlock.nextListElement=null;
    basicBlock.flags|=Label.FLAG_REACHABLE;
    int maxBlockStackSize=basicBlock.frame.getInputStackSize() + basicBlock.outputStackMax;
    if (maxBlockStackSize > maxStackSize) {
      maxStackSize=maxBlockStackSize;
    }
    Edge outgoingEdge=basicBlock.outgoingEdges;
    while (outgoingEdge != null) {
      Label successorBlock=outgoingEdge.successor.getCanonicalInstance();
      boolean successorBlockChanged=basicBlock.frame.merge(symbolTable,successorBlock.frame,outgoingEdge.info);
      if (successorBlockChanged && successorBlock.nextListElement == null) {
        successorBlock.nextListElement=listOfBlocksToProcess;
        listOfBlocksToProcess=successorBlock;
      }
      outgoingEdge=outgoingEdge.nextEdge;
    }
  }
  Label basicBlock=firstBasicBlock;
  while (basicBlock != null) {
    if ((basicBlock.flags & (Label.FLAG_JUMP_TARGET | Label.FLAG_REACHABLE)) == (Label.FLAG_JUMP_TARGET | Label.FLAG_REACHABLE)) {
      basicBlock.frame.accept(this);
    }
    if ((basicBlock.flags & Label.FLAG_REACHABLE) == 0) {
      Label nextBasicBlock=basicBlock.nextBasicBlock;
      int startOffset=basicBlock.bytecodeOffset;
      int endOffset=(nextBasicBlock == null ? code.length : nextBasicBlock.bytecodeOffset) - 1;
      if (endOffset >= startOffset) {
        for (int i=startOffset; i < endOffset; ++i) {
          code.data[i]=Opcodes.NOP;
        }
        code.data[endOffset]=(byte)Opcodes.ATHROW;
        int frameIndex=visitFrameStart(startOffset,0,1);
        currentFrame[frameIndex]=Frame.getAbstractTypeFromInternalName(symbolTable,"java/lang/Throwable");
        visitFrameEnd();
        firstHandler=Handler.removeRange(firstHandler,basicBlock,nextBasicBlock);
        maxStackSize=Math.max(maxStackSize,1);
      }
    }
    basicBlock=basicBlock.nextBasicBlock;
  }
  this.maxStack=maxStackSize;
}
