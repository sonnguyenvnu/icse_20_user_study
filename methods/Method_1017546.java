@Override public StateMachineContext<S,E> read(String contextObj) throws Exception {
  return repository.getContext(contextObj);
}
