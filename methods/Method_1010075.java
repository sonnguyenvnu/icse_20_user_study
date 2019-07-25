public Set<Instruction> pred(){
  Set<Instruction> result=new HashSet<>();
  for (  ProgramState ps : new ProgramState(this,false).pred()) {
    result.add(ps.getInstruction());
  }
  for (  ProgramState ps : new ProgramState(this,true).pred()) {
    result.add(ps.getInstruction());
  }
  return result;
}
