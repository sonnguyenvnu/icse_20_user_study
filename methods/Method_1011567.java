/** 
 * @deprecated use {@link jetbrains.mps.execution.api.commands.ProcessHandlerBuilder#append(String) } instead
 */
@Deprecated @ToRemove(version=2018.3) public ProcessHandlerBuilder append(@Nullable String command){
  if (!((command == null || command.length() == 0))) {
    ListSequence.fromList(myCommandLine).addSequence(Sequence.fromIterable(splitCommandInParts(command)));
  }
  return this;
}
