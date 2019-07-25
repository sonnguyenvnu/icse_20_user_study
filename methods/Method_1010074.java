public Set<Instruction> succ(){
  Set<Instruction> result=new HashSet<>();
  for (  ProgramState ps : new ProgramState(this,false).succ()) {
    result.add(ps.getInstruction());
  }
  for (  ProgramState ps : new ProgramState(this,true).succ()) {
    result.add(ps.getInstruction());
  }
  return result;
}
