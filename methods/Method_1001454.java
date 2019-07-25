final public CommandExecutionResult kill(){
  if (current().kill() == false) {
    return CommandExecutionResult.error("kill cannot be used here");
  }
  return CommandExecutionResult.ok();
}
