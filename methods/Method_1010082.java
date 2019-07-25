protected void init(){
  add(new EndInstruction());
  collectVariables();
  buildBlockInfos();
  buildInstructionCaches();
  sanityCheck();
}
