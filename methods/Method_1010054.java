@Override public VarSet merge(Program p,List<VarSet> input){
  VarSet result=new VarSet(p);
  for (  VarSet inputSet : input) {
    result.addAll(inputSet);
  }
  return result;
}
