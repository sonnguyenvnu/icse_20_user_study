void showErrorDialogAndQuit(String message){
  mFinishOnDismissNameDialog=false;
  new AlertDialog.Builder(this).setMessage(message).setOnDismissListener(dialog -> finish()).setPositiveButton(android.R.string.ok,(dialog,which) -> finish()).show();
}
