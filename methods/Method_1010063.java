@Override public Set<WriteInstruction> fun(Set<WriteInstruction> input,ProgramState s){
  Instruction instruction=s.getInstruction();
  Set<WriteInstruction> result=input;
  if (instruction instanceof WriteInstruction) {
    WriteInstruction write=(WriteInstruction)instruction;
    for (    WriteInstruction item : new HashSet<>(result)) {
      Object variable=write.getVariable();
      if (variable != null && variable.equals(item.getVariable())) {
        result.remove(item);
      }
    }
    result.add(write);
  }
  return result;
}
