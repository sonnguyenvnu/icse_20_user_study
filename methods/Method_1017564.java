@Override protected MongoDbRepositoryStateMachine build(StateMachineContext<S,E> context,Object contextObj,byte[] serialisedContext){
  MongoDbRepositoryStateMachine mongodbRepositoryStateMachine=new MongoDbRepositoryStateMachine();
  mongodbRepositoryStateMachine.setId(contextObj.toString());
  mongodbRepositoryStateMachine.setMachineId(context.getId());
  mongodbRepositoryStateMachine.setState(context.getState().toString());
  mongodbRepositoryStateMachine.setStateMachineContext(serialisedContext);
  return mongodbRepositoryStateMachine;
}
