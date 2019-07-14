public void addUncalled(FunType cl){
  if (!called.contains(cl.getFunc())) {
    uncalled.add(cl);
  }
}
