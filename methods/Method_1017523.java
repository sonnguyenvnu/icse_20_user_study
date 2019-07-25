@Override public void configure(StateMachineTransitionBuilder<S,E> builder) throws Exception {
  builder.addDefaultHistory(source,target);
}
