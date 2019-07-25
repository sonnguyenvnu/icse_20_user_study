@Override public boolean select(){
  if (times == INFINITE) {
    return true;
  }
  return times > selector.selectCounts();
}
