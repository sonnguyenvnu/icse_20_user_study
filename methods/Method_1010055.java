@Override public VarSet fun(VarSet input,ProgramState s){
  Instruction instruction=s.getInstruction();
  VarSet result=input;
  if (instruction instanceof ReadInstruction) {
    ReadInstruction read=(ReadInstruction)instruction;
    result.add(read.getVariableIndex());
  }
  if (instruction instanceof WriteInstruction) {
    WriteInstruction write=(WriteInstruction)instruction;
    result.remove(write.getVariableIndex());
  }
  return result;
}
