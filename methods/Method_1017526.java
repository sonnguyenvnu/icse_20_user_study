@Override public void configure(StateMachineTransitionBuilder<S,E> builder) throws Exception {
  builder.addJoin(target,sources);
}
