@OnUpdateState static void updateState(StateValue<Integer> state){
  state.set((state.get() + 1) % 6);
}
