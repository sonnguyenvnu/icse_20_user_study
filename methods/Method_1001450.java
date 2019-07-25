public void stop(){
  manageSwimlaneStrategy();
  final InstructionStop ins=new InstructionStop(swinlanes.getCurrentSwimlane(),nextLinkRenderer());
  if (manageSpecialStopEndAfterEndWhile(ins)) {
    return;
  }
  current().add(ins);
}
