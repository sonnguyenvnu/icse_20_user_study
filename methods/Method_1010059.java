@Override public Boolean merge(Program p,List<Boolean> input){
  for (  Boolean value : input) {
    if (value) {
      return true;
    }
  }
  return false;
}
