@Override public Set<ReadInstruction> merge(Program p,List<Set<ReadInstruction>> input){
  Set<ReadInstruction> result=new HashSet<>();
  for (  Set<ReadInstruction> i : input) {
    result.addAll(i);
  }
  return result;
}
