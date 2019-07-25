@Override public List<ProgramState> succ(ProgramState s){
  if (!s.isReturnMode()) {
    return super.succ(s);
  }
 else {
    List<ProgramState> result=new ArrayList<>();
    TryFinallyInfo info=getEnclosingBlock();
    if (info != null) {
      if (isBefore(info.getFinally())) {
        result.add(new ProgramState(info.getFinally(),true));
      }
 else {
        result.add(new ProgramState(info.getEndTry(),true));
      }
    }
 else {
      result.add(new ProgramState(getProgram().getEnd(),true));
    }
    return result;
  }
}
