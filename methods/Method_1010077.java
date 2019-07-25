public List<ProgramState> pred(ProgramState s){
  List<ProgramState> result=new ArrayList<>();
  if (this != getProgram().getStart()) {
    Instruction prev=getProgram().get(getIndex() - 1);
    if (!(prev instanceof RetInstruction) && !(prev instanceof JumpInstruction) && !(prev instanceof EndTryInstruction && s.isReturnMode())) {
      result.add(new ProgramState(prev,s.isReturnMode()));
    }
  }
  for (  Instruction jump : myJumps) {
    result.add(new ProgramState(jump,s.isReturnMode()));
  }
  return result;
}
