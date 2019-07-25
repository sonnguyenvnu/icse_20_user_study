@Override public Set<ReadInstruction> fun(Set<ReadInstruction> input,ProgramState s){
  Instruction instruction=s.getInstruction();
  Set<ReadInstruction> result=input;
  if (instruction instanceof WriteInstruction) {
    WriteInstruction write=(WriteInstruction)instruction;
    for (    ReadInstruction item : new HashSet<>(result)) {
      Object variable=write.getVariable();
      if (variable != null && variable.equals(item.getVariable())) {
        result.remove(item);
      }
    }
  }
  if (instruction instanceof ReadInstruction) {
    result.add((ReadInstruction)instruction);
  }
  return result;
}
