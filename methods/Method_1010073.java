@Override public List<ProgramState> succ(ProgramState s){
  List<ProgramState> result=super.succ(s);
  result.add(new ProgramState(getProgram().get(myJumpTo),s.isReturnMode()));
  return result;
}
