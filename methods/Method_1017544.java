@Override public final StateMachine<S,E> restore(StateMachine<S,E> stateMachine,T contextObj) throws Exception {
  final StateMachineContext<S,E> context=stateMachinePersist.read(contextObj);
  stateMachine.stopReactively().block();
  stateMachine.getStateMachineAccessor().doWithAllRegions(function -> function.resetStateMachine(context));
  stateMachine.startReactively().block();
  return stateMachine;
}
