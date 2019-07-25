@Override public List<ProgramState> pred(ProgramState s){
  List<ProgramState> result=new ArrayList<>();
  if (s.isReturnMode()) {
    for (    RetInstruction ret : myReturns) {
      if (ret.getEnclosingBlock() == null) {
        result.add(new ProgramState(ret,false));
        result.add(new ProgramState(ret,true));
      }
    }
    for (    TryFinallyInfo info : myRootTryFinallies) {
      result.add(new ProgramState(info.getEndTry(),true));
    }
  }
  result.addAll(super.pred(s));
  return result;
}
