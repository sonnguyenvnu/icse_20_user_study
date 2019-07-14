/** 
 * Finds the basic blocks that belong to the subroutine starting with the basic block corresponding to this label, and marks these blocks as belonging to this subroutine. This method follows the control flow graph to find all the blocks that are reachable from the current basic block WITHOUT following any jsr target. <p>Note: a precondition and postcondition of this method is that all labels must have a null {@link #nextListElement}.
 * @param subroutineId the id of the subroutine starting with the basic block corresponding tothis label.
 */
final void markSubroutine(final short subroutineId){
  Label listOfBlocksToProcess=this;
  listOfBlocksToProcess.nextListElement=EMPTY_LIST;
  while (listOfBlocksToProcess != EMPTY_LIST) {
    Label basicBlock=listOfBlocksToProcess;
    listOfBlocksToProcess=listOfBlocksToProcess.nextListElement;
    basicBlock.nextListElement=null;
    if (basicBlock.subroutineId == 0) {
      basicBlock.subroutineId=subroutineId;
      listOfBlocksToProcess=basicBlock.pushSuccessors(listOfBlocksToProcess);
    }
  }
}
