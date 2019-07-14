/** 
 * Finds the basic blocks that end a subroutine starting with the basic block corresponding to this label and, for each one of them, adds an outgoing edge to the basic block following the given subroutine call. In other words, completes the control flow graph by adding the edges corresponding to the return from this subroutine, when called from the given caller basic block. <p>Note: a precondition and postcondition of this method is that all labels must have a null {@link #nextListElement}.
 * @param subroutineCaller a basic block that ends with a jsr to the basic block corresponding tothis label. This label is supposed to correspond to the start of a subroutine.
 */
final void addSubroutineRetSuccessors(final Label subroutineCaller){
  Label listOfProcessedBlocks=EMPTY_LIST;
  Label listOfBlocksToProcess=this;
  listOfBlocksToProcess.nextListElement=EMPTY_LIST;
  while (listOfBlocksToProcess != EMPTY_LIST) {
    Label basicBlock=listOfBlocksToProcess;
    listOfBlocksToProcess=basicBlock.nextListElement;
    basicBlock.nextListElement=listOfProcessedBlocks;
    listOfProcessedBlocks=basicBlock;
    if ((basicBlock.flags & FLAG_SUBROUTINE_END) != 0 && basicBlock.subroutineId != subroutineCaller.subroutineId) {
      basicBlock.outgoingEdges=new Edge(basicBlock.outputStackSize,subroutineCaller.outgoingEdges.successor,basicBlock.outgoingEdges);
    }
    listOfBlocksToProcess=basicBlock.pushSuccessors(listOfBlocksToProcess);
  }
  while (listOfProcessedBlocks != EMPTY_LIST) {
    Label newListOfProcessedBlocks=listOfProcessedBlocks.nextListElement;
    listOfProcessedBlocks.nextListElement=null;
    listOfProcessedBlocks=newListOfProcessedBlocks;
  }
}
