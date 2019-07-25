@Deactivate public void deactivate(){
  helpCommand.setConsoleCommandsContainer(null);
  for (  Map.Entry<ConsoleCommandExtension,ServiceRegistration<?>> entry : commands.entrySet()) {
    if (entry.getValue() != null) {
      unregisterCommand(entry.getValue());
      entry.setValue(null);
    }
  }
  this.bc=null;
}
