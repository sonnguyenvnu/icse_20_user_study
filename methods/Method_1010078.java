@Override public List<ProgramState> succ(ProgramState s){
  List<ProgramState> result=new ArrayList<>();
  result.add(new ProgramState(getProgram().get(myJumpTo),s.isReturnMode()));
  return result;
}
