@Override public void update(){
  super.update();
  lastAction=currentAction;
  currentAction=Action.IDLE;
  lastConsoleMessagesBuffer=new ArrayList<>(consoleMessagesBuffer);
  consoleMessagesBuffer.clear();
  for (  HardwareModule module : hardwareAddresses.values()) {
    module.update();
  }
  if (getAge() >= NonPlayerCharacter.LIFETIME) {
    setDead(true);
  }
  getTask().tick(this);
}
