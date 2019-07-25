@Override public Set<InstructionWrapper> succ(){
  Set<InstructionWrapper> result=new HashSet<InstructionWrapper>();
  for (  Instruction instruction : this.myInstruction.succ()) {
    result.add(new InstructionWrapper(instruction));
  }
  return result;
}
