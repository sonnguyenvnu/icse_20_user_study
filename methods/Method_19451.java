@OnUpdateState static void remeasureForUpdatedText(StateValue<Integer> measureSeqNumber){
  measureSeqNumber.set(measureSeqNumber.get() + 1);
}
