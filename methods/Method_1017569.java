@Override public StateMachineContext<S,E> read(Object contextObj) throws Exception {
  M repositoryStateMachine=getRepository().findById(contextObj.toString()).orElse(null);
  if (repositoryStateMachine != null) {
    StateMachineContext<S,E> context=serialisationService.deserialiseStateMachineContext(repositoryStateMachine.getStateMachineContext());
    ;
    if (context != null && context.getChilds() != null && context.getChilds().isEmpty() && context.getChildReferences() != null) {
      List<StateMachineContext<S,E>> contexts=new ArrayList<>();
      for (      String childRef : context.getChildReferences()) {
        repositoryStateMachine=getRepository().findById(childRef).orElse(null);
        if (repositoryStateMachine != null) {
          contexts.add(serialisationService.deserialiseStateMachineContext(repositoryStateMachine.getStateMachineContext()));
        }
      }
      return new DefaultStateMachineContext<S,E>(contexts,context.getState(),context.getEvent(),context.getEventHeaders(),context.getExtendedState(),context.getHistoryStates(),context.getId());
    }
 else {
      return context;
    }
  }
  return null;
}
