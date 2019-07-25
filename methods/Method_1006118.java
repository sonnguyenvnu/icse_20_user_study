public void install(ComboBox<T> comboBox){
  comboBox.setButtonCell(this.call(null));
  comboBox.setCellFactory(this);
}
