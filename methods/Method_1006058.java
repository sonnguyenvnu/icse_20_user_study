@FXML private void initialize(){
  viewModel=new AboutDialogViewModel(dialogService,clipBoardManager,buildInfo);
  textAreaVersions.setText(viewModel.getVersionInfo());
}
