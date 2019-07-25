@Override public void configure(StateMachineTransitionBuilder<S,E> builder) throws Exception {
  builder.addFork(source,targets);
}
