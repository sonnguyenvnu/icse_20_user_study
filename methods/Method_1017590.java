@Override public void configure(StateMachineStateConfigurer<States,Events> states) throws Exception {
  states.withStates().initial(States.LOCKED).states(EnumSet.allOf(States.class));
}
