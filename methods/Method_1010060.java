@Override public Boolean fun(Boolean input,ProgramState s){
  if (s.isStart()) {
    return true;
  }
  return input;
}
