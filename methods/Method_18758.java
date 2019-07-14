@OnUpdateState static void onUpdateState(StateValue<Integer> extra,@Param int newExtra){
  extra.set(newExtra);
}
