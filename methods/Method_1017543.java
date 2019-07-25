@Override public final void persist(StateMachine<S,E> stateMachine,T contextObj) throws Exception {
  stateMachinePersist.write(buildStateMachineContext(stateMachine),contextObj);
}
