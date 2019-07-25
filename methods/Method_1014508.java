public int execute(int timeout){
  long startTime=System.currentTimeMillis();
  int counter=0;
  status.clear();
  registerSetSize=registerSet.size();
  while (!status.isBreakFlag()) {
    counter++;
    if (counter % 10000 == 0) {
      if (System.currentTimeMillis() > (startTime + timeout)) {
        LogManager.LOGGER.fine("CPU Timeout " + this + " after " + counter + "instructions (" + timeout + "ms): " + (double)counter / ((double)timeout / 1000) / 1000000 + "MHz");
        memory.set(EXECUTION_COST_ADDR,timeout);
        memory.set(EXECUTED_INS_ADDR,Util.getHigherWord(counter));
        memory.set(EXECUTED_INS_ADDR + 1,Util.getLowerWord(counter));
        return timeout;
      }
    }
    int machineCode=memory.get(ip);
    Instruction instruction=instructionSet.get(machineCode & 0x03F);
    int source=(machineCode >> 11) & 0x001F;
    int destination=(machineCode >> 6) & 0x001F;
    executeInstruction(instruction,source,destination);
  }
  int elapsed=(int)(System.currentTimeMillis() - startTime);
  memory.set(EXECUTION_COST_ADDR,elapsed);
  memory.set(EXECUTED_INS_ADDR,Util.getHigherWord(counter));
  memory.set(EXECUTED_INS_ADDR + 1,Util.getLowerWord(counter));
  return elapsed;
}
