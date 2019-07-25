private void revert(ListView<Integer> histories,History history){
  Input.get().undo(history.name);
  regen(histories,history);
  currentRevert.setDisable(history.size() == 0);
}
