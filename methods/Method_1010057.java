@Override public VarSet merge(Program p,List<VarSet> input){
  if (input.isEmpty()) {
    return initial(p);
  }
  VarSet result=new VarSet(p,false);
  for (  VarSet anInput : input) {
    result.addAll(anInput);
  }
  return result;
}
