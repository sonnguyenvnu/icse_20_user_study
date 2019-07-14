/** 
 * If this command has completed execution either successfully, via fallback or failure.
 * @return boolean
 */
public boolean isExecutionComplete(){
  return commandState.get() == CommandState.TERMINAL;
}
