@Override protected RedisRepositoryStateMachine build(StateMachineContext<S,E> context,Object contextObj,byte[] serialisedContext){
  RedisRepositoryStateMachine redisRepositoryStateMachine=new RedisRepositoryStateMachine();
  redisRepositoryStateMachine.setId(contextObj.toString());
  redisRepositoryStateMachine.setMachineId(context.getId());
  redisRepositoryStateMachine.setState(context.getState().toString());
  redisRepositoryStateMachine.setStateMachineContext(serialisedContext);
  return redisRepositoryStateMachine;
}
