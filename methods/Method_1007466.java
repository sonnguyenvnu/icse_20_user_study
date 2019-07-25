/** 
 * This basic block's last instruction is JSR. This method initiates a subgraph traversal to identify the called subroutine's boundaries and to make all encountered RET instructions point back to this BB's follower, in essence turning it to a goto. The reason for not actually turning it into a GOTO is that if we don't find any pausable methods in a subroutine, then during code generation we'll simply use the original code. The duplication is still required for flow analysis. The VM spec is fuzzy on what constitutes the boundaries of a subroutine. We consider the following situations invalid, even though the verifier is ok with it: (a) looping back to itself (b) encountering xRETURN in a subroutine inline() traverses the graph creating copies of BasicBlocks and labels and keeps a mapping between the old and the new. In the second round, it copies instructions translating any that have labels (branch and switch instructions).
 * @return mapping of orig basic blocks to new.
 */
ArrayList<BasicBlock> inline() throws KilimException {
  HashMap<BasicBlock,BasicBlock> bbCopyMap=null;
  HashMap<LabelNode,LabelNode> labelCopyMap=null;
  BasicBlock targetBB=successors.get(0);
  LabelNode returnToLabel=flow.getOrCreateLabelAtPos(endPos + 1);
  BasicBlock returnToBB=flow.getOrCreateBasicBlock(returnToLabel);
  boolean isPausableSub=targetBB.hasFlag(PAUSABLE_SUB);
  if (!targetBB.hasFlag(SUBROUTINE_CLAIMED)) {
    targetBB.setFlag(SUBROUTINE_CLAIMED);
    for (    BasicBlock b : targetBB.getSubBlocks()) {
      if (b.lastInstruction() == RET) {
        assert b.successors.size() == 0 : this.toString();
        b.addSuccessor(returnToBB);
      }
    }
    return null;
  }
  bbCopyMap=new HashMap<BasicBlock,BasicBlock>(10);
  labelCopyMap=new HashMap<LabelNode,LabelNode>(10);
  successors.clear();
  targetBB.dupBBAndLabels(isPausableSub,bbCopyMap,labelCopyMap,returnToBB);
  addSuccessor(bbCopyMap.get(targetBB));
  return dupCopyContents(isPausableSub,targetBB,returnToBB,bbCopyMap,labelCopyMap);
}
