@Override public List<ProgramState> succ(ProgramState s){
  List<ProgramState> result=new ArrayList<>();
  TryFinallyInfo blockInfo=getEnclosingBlock();
  if (blockInfo == null) {
    result.add(new ProgramState(getProgram().getEnd(),true));
  }
 else {
    if (isBefore(blockInfo.getFinally())) {
      result.add(new ProgramState(blockInfo.getFinally(),true));
    }
 else {
      result.add(new ProgramState(blockInfo.getEndTry(),true));
    }
  }
  return result;
}
