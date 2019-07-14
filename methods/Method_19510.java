@OnUpdateState static void toggleABT(StateValue<Boolean> autoBoundsTransitionEnabled){
  autoBoundsTransitionEnabled.set(!autoBoundsTransitionEnabled.get());
}
