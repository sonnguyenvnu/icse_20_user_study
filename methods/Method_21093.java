private void showAndroidPayError(final @NonNull Integer error){
  final ConfirmDialog dialog=new ConfirmDialog(this,this.androidPayErrorTitleString + " (" + error.toString() + ")",this.androidPayErrorMessageString);
  dialog.setOnDismissListener(d -> this.back());
  dialog.show();
}
