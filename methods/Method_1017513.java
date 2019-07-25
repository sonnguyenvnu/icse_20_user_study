@Override public final void init(StateMachineConfigBuilder<S,E> config) throws Exception {
  config.setSharedObject(StateMachineModelBuilder.class,getStateMachineModelBuilder());
  config.setSharedObject(StateMachineTransitionBuilder.class,getStateMachineTransitionBuilder());
  config.setSharedObject(StateMachineStateBuilder.class,getStateMachineStateBuilder());
  config.setSharedObject(StateMachineConfigurationBuilder.class,getStateMachineConfigurationBuilder());
}
