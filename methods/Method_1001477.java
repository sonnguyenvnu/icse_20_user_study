final public boolean kill(){
  if (all.size() == 0) {
    return false;
  }
  return getLast().kill();
}
