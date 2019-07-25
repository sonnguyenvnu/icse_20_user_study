@Override public List<ProgramState> pred(ProgramState s){
  if (s.isReturnMode()) {
    List<ProgramState> result=new ArrayList<>();
    for (    RetInstruction ret : myReturns) {
      result.add(new ProgramState(ret,false));
      result.add(new ProgramState(ret,true));
    }
    for (    TryFinallyInfo childInfo : myChildTryFinallies) {
      if (childInfo.getEndTry().isBefore(this)) {
        result.add(new ProgramState(childInfo.getEndTry(),true));
      }
    }
    result.addAll(super.pred(s));
    return result;
  }
 else {
    return super.pred(s);
  }
}
