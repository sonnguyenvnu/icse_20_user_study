public void analyze() throws KilimException {
  preAssignCatchHandlers();
  buildBasicBlocks();
  if (basicBlocks.size() == 0)   return;
  consolidateBasicBlocks();
  assignCatchHandlers();
  inlineSubroutines();
  doLiveVarAnalysis();
  dataFlow();
  this.labelToBBMap=null;
}
