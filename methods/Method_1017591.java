@Override public void configure(StateMachineTransitionConfigurer<States,Events> transitions) throws Exception {
  transitions.withExternal().source(States.LOCKED).target(States.UNLOCKED).event(Events.COIN).and().withExternal().source(States.UNLOCKED).target(States.LOCKED).event(Events.PUSH);
}
