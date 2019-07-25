@Override public Set<WriteInstruction> merge(Program p,List<Set<WriteInstruction>> input){
  Set<WriteInstruction> result=new HashSet<>();
  for (  Set<WriteInstruction> i : input) {
    result.addAll(i);
  }
  return result;
}
