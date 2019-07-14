public void showAlertDialog(AlertDialog.Builder builder){
  if (parentActivity == null) {
    return;
  }
  try {
    if (visibleDialog != null) {
      visibleDialog.dismiss();
      visibleDialog=null;
    }
  }
 catch (  Exception e) {
    FileLog.e(e);
  }
  try {
    visibleDialog=builder.show();
    visibleDialog.setCanceledOnTouchOutside(true);
    visibleDialog.setOnDismissListener(dialog -> visibleDialog=null);
  }
 catch (  Exception e) {
    FileLog.e(e);
  }
}
