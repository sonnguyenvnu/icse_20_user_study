/** 
 * Performs renaming transformation, modifying the method's instructions in-place.
 */
@Override public void run(){
  ssaMeth.forEachBlockDepthFirstDom(new SsaBasicBlock.Visitor(){
    @Override public void visitBlock(    SsaBasicBlock block,    SsaBasicBlock unused){
      new BlockRenamer(block).process();
    }
  }
);
  ssaMeth.setNewRegCount(nextSsaReg);
  ssaMeth.onInsnsChanged();
  if (DEBUG) {
    System.out.println("SSA\tRop");
    int[] versions=new int[ropRegCount];
    int sz=ssaRegToRopReg.size();
    for (int i=0; i < sz; i++) {
      int ropReg=ssaRegToRopReg.get(i);
      System.out.println(i + "\t" + ropReg + "[" + versions[ropReg] + "]");
      versions[ropReg]++;
    }
  }
}
