@OnUpdateState static void updateState(StateValue<Boolean> state){
  state.set(!state.get());
}
