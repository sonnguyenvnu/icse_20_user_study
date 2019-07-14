@OnUpdateState static void updateSelection(StateValue<String> selection,@Param String newSelection){
  selection.set(newSelection);
}
