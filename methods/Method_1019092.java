private void regen(ListView<Integer> histories,History history){
  times=history.getFileTimes();
  histories.getItems().clear();
  for (int i=0; i < history.size(); i++) {
    histories.getItems().add(i);
  }
}
