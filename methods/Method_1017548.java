public T process(StateMachineRuntime<S,E> stateMachineRuntime) throws Exception {
  ParametersWrapper<S,E> wrapper=new ParametersWrapper<S,E>(stateMachineRuntime.getStateContext());
  return processInternal(wrapper);
}
