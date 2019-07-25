@Override public List<ProgramState> pred(ProgramState s){
  List<ProgramState> result=super.pred(s);
  if (s.isReturnMode()) {
    for (    TryFinallyInfo child : myInfo.getChildren()) {
      if (child.getFinally().isAfter(myInfo.getFinally())) {
        result.add(new ProgramState(child.getEndTry(),true));
      }
    }
    for (    RetInstruction ret : myReturns) {
      result.add(new ProgramState(ret,true));
      result.add(new ProgramState(ret,false));
    }
  }
  return result;
}
