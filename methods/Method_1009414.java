@Override public void chooser(ActionEvent actionEvent){
  DirectoryChooser directoryChooser=new DirectoryChooser();
  directoryChooser.setTitle(promptText);
  File openDialog=directoryChooser.showDialog(null);
  if (Objects.nonNull(openDialog)) {
    property.setValue(openDialog.toPath().toString());
  }
}
