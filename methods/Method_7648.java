public Dialog showDialog(Dialog dialog,boolean allowInTransition,final Dialog.OnDismissListener onDismissListener){
  if (dialog == null || parentLayout == null || parentLayout.animationInProgress || parentLayout.startedTracking || !allowInTransition && parentLayout.checkTransitionAnimation()) {
    return null;
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
    visibleDialog=dialog;
    visibleDialog.setCanceledOnTouchOutside(true);
    visibleDialog.setOnDismissListener(dialog1 -> {
      if (onDismissListener != null) {
        onDismissListener.onDismiss(dialog1);
      }
      onDialogDismiss(visibleDialog);
      visibleDialog=null;
    }
);
    visibleDialog.show();
    return visibleDialog;
  }
 catch (  Exception e) {
    FileLog.e(e);
  }
  return null;
}
