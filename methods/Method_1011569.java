public ProcessHandlerBuilder append(String... command){
  for (  String commandPart : Sequence.fromArray(command)) {
    if (!((commandPart == null || commandPart.length() == 0))) {
      ListSequence.fromList(myCommandLine).addSequence(Sequence.fromIterable(splitCommandInParts(commandPart)));
    }
  }
  return this;
}
