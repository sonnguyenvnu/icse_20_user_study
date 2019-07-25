@Override protected JpaRepositoryStateMachine build(StateMachineContext<S,E> context,Object contextObj,byte[] serialisedContext){
  JpaRepositoryStateMachine jpaRepositoryStateMachine=new JpaRepositoryStateMachine();
  jpaRepositoryStateMachine.setMachineId(context.getId());
  jpaRepositoryStateMachine.setState(context.getState() != null ? context.getState().toString() : null);
  jpaRepositoryStateMachine.setStateMachineContext(serialisedContext);
  return jpaRepositoryStateMachine;
}
