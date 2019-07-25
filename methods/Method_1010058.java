@Override public VarSet fun(VarSet input,ProgramState s){
  Instruction instruction=s.getInstruction();
  VarSet result=input;
  if (s.isStart()) {
    result.clear();
  }
  if (instruction instanceof WriteInstruction && !myExclusions.contains(instruction)) {
    WriteInstruction write=(WriteInstruction)instruction;
    result.add(write.getVariableIndex());
  }
  if (instruction instanceof JumpInstruction || instruction instanceof IfJumpInstruction) {
    final int to;
    if (instruction instanceof JumpInstruction) {
      to=((JumpInstruction)instruction).getJumpTo();
    }
 else {
      to=((IfJumpInstruction)instruction).getJumpTo();
    }
    final int current=s.getInstruction().getIndex();
    if (to < current) {
      final Program program=instruction.getProgram();
      for (      Object var : program.getVariables()) {
        final List<Instruction> instructions=program.getInstructionsFor(var);
        if (!instructions.isEmpty()) {
          if (to < instructions.get(0).getIndex()) {
            result.remove(var);
          }
 else {
            result.add(var);
          }
        }
      }
    }
  }
  return result;
}
