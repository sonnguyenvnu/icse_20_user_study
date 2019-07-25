@FXML public void initialize(){
  visualizer.setDecoration(new IconValidationDecorator());
  viewModel.findStringProperty().bind(findField.textProperty());
  viewModel.replaceStringProperty().bind(replaceField.textProperty());
  viewModel.fieldStringProperty().bind(limitFieldInput.textProperty());
  viewModel.selectOnlyProperty().bind(selectFieldOnly.selectedProperty());
  viewModel.allFieldReplaceProperty().bind(allReplace.selectedProperty());
}
