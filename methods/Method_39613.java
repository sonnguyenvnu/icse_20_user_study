/** 
 * Adds a successor to  {@link #currentBasicBlock} in the control flow graph.
 * @param info information about the control flow edge to be added.
 * @param successor the successor block to be added to the current basic block.
 */
private void addSuccessorToCurrentBasicBlock(final int info,final Label successor){
  currentBasicBlock.outgoingEdges=new Edge(info,successor,currentBasicBlock.outgoingEdges);
}
