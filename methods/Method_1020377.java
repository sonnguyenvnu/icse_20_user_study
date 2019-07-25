/** 
 * Applies the optimization.
 */
private void run(){
  int regSz=ssaMeth.getRegCount();
  ArrayList<TypedConstant> constantList=getConstsSortedByCountUse();
  int toCollect=Math.min(constantList.size(),MAX_COLLECTED_CONSTANTS);
  SsaBasicBlock start=ssaMeth.getEntryBlock();
  HashMap<TypedConstant,RegisterSpec> newRegs=new HashMap<TypedConstant,RegisterSpec>(toCollect);
  for (int i=0; i < toCollect; i++) {
    TypedConstant cst=constantList.get(i);
    RegisterSpec result=RegisterSpec.make(ssaMeth.makeNewSsaReg(),cst);
    Rop constRop=Rops.opConst(cst);
    if (constRop.getBranchingness() == Rop.BRANCH_NONE) {
      start.addInsnToHead(new PlainCstInsn(Rops.opConst(cst),SourcePosition.NO_INFO,result,RegisterSpecList.EMPTY,cst));
    }
 else {
      SsaBasicBlock entryBlock=ssaMeth.getEntryBlock();
      SsaBasicBlock successorBlock=entryBlock.getPrimarySuccessor();
      SsaBasicBlock constBlock=entryBlock.insertNewSuccessor(successorBlock);
      constBlock.replaceLastInsn(new ThrowingCstInsn(constRop,SourcePosition.NO_INFO,RegisterSpecList.EMPTY,StdTypeList.EMPTY,cst));
      SsaBasicBlock resultBlock=constBlock.insertNewSuccessor(successorBlock);
      PlainInsn insn=new PlainInsn(Rops.opMoveResultPseudo(result.getTypeBearer()),SourcePosition.NO_INFO,result,RegisterSpecList.EMPTY);
      resultBlock.addInsnToHead(insn);
    }
    newRegs.put(cst,result);
  }
  updateConstUses(newRegs,regSz);
}
