private @NonNull AlertDialog lazyConfirmationDialog(){
  if (this.confirmationDialog == null) {
    this.confirmationDialog=new AlertDialog.Builder(this).setMessage(this.surveyResponseSubmittedString).setPositiveButton(this.okString,(__,___) -> this.viewModel.inputs.okButtonClicked()).create();
  }
  return this.confirmationDialog;
}
