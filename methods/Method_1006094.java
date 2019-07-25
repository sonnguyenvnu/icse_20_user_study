@FXML private void initialize(){
  viewModel=new AdvancedCiteDialogViewModel();
  inPar.selectedProperty().bindBidirectional(viewModel.citeInParProperty());
  inText.selectedProperty().bindBidirectional(viewModel.citeInTextProperty());
  pageInfo.textProperty().bindBidirectional(viewModel.pageInfoProperty());
}
