@Override public void startEdit(){
  Platform.runLater(() -> {
    textField.selectAll();
    textField.requestFocus();
  }
);
}
