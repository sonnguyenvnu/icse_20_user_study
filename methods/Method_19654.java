@OnUpdateState static void incrementClickCount(StateValue<Integer> count){
  count.set(count.get() + 1);
}
