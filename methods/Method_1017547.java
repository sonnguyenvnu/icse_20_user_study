/** 
 * Handle container using a  {@link StateMachineRuntimeProcessor}.
 * @param stateMachineRuntime the state machine runtime
 * @return the result value
 */
public Object handle(StateMachineRuntime<S,E> stateMachineRuntime){
  return processor.process(stateMachineRuntime);
}
