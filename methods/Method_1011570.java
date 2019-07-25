public ProcessHandlerBuilder append(@NotNull List<String> command){
  for (  String commandPart : command) {
    append(commandPart);
  }
  return this;
}
