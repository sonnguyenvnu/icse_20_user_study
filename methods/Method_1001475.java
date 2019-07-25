final public boolean kill(){
  if (endifCalled) {
    for (    Branch branch : thens) {
      if (branch.getLast().kill() == false) {
        return false;
      }
      if (elseBranch != null && elseBranch.getLast() != null && elseBranch.getLast().kill() == false) {
        return false;
      }
      return true;
    }
  }
  return current.kill();
}
