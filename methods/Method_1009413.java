@Override public void chooser(ActionEvent actionEvent){
  FileChooser fileChooser=new FileChooser();
  fileChooser.setTitle(promptText);
  File openDialog=fileChooser.showOpenDialog(null);
  if (Objects.nonNull(openDialog)) {
    property.setValue(openDialog.toPath().toString());
  }
}
