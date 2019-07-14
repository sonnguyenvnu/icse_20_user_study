public void off(int slot){
  offCommands[slot].execute();
  stack.push(offCommands[slot]);
}
