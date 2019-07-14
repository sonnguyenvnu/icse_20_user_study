@OnUpdateState static void updateState(StateValue<Boolean> top){
  top.set(!top.get());
}
