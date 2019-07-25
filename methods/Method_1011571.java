public ProcessHandlerBuilder append(@Nullable CommandPart commandPart){
  if (commandPart != null) {
    ListSequence.fromList(myCommandLine).addSequence(ListSequence.fromList(commandPart.getCommandList()));
  }
  return this;
}
