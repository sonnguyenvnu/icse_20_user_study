public List<ProgramState> succ(ProgramState s){
  List<ProgramState> result=new ArrayList<>();
  result.add(new ProgramState(getProgram().get(getIndex() + 1),s.isReturnMode()));
  return result;
}
