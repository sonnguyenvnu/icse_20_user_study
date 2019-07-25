public Program copy(){
  Program program=new Program();
  for (  Instruction i : myInstructions) {
    program.add(i);
  }
  return program;
}
