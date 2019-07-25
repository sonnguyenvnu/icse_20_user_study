@Override public VarSet merge(Program p,List<VarSet> input){
  if (input.isEmpty()) {
    return initial(p);
  }
  VarSet result=new VarSet(p,true);
  for (  VarSet anInput : input) {
    result.retainAll(anInput);
  }
  return result;
}
