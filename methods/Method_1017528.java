@Override public void configure(StateMachineTransitionBuilder<S,E> builder) throws Exception {
  builder.addTransition(getSource(),getTarget(),getState(),getEvent(),getPeriod(),getCount(),getActions(),getGuard(),TransitionKind.LOCAL,getSecurityRule());
}
