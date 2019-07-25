@Override public VarSet fun(VarSet input,ProgramState s){
  Instruction instruction=s.getInstruction();
  VarSet result=input;
  if (s.isStart()) {
    result.clear();
  }
  if (instruction instanceof WriteInstruction) {
    WriteInstruction write=(WriteInstruction)instruction;
    result.add(write.getVariableIndex());
  }
  return result;
}
