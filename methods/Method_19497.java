@OnUpdateState static void updateState(StateValue<Boolean> left){
  left.set(left.get() == true ? false : true);
}
