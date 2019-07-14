@Override public void updateItem(T item,boolean empty){
  Platform.runLater(() -> {
    textField.selectAll();
    textField.requestFocus();
  }
);
}
