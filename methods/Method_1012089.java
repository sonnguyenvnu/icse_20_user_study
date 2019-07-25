@Override public Set<InstructionWrapper> pred(){
  Set<InstructionWrapper> result=new HashSet<InstructionWrapper>();
  for (  Instruction instruction : this.myInstruction.pred()) {
    result.add(new InstructionWrapper(instruction));
  }
  return result;
}
