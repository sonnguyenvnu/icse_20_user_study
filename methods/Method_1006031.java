@FXML public void initialize(){
  viewModel=new ContentSelectorDialogViewModel(basePanel,dialogService);
  initFieldNameComponents();
  initKeywordsComponents();
}
