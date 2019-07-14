private void showCancelAlert(){
  if (!canCacnel || cancelDialog != null) {
    return;
  }
  Builder builder=new Builder(getContext());
  builder.setTitle(LocaleController.getString("AppName",R.string.AppName));
  builder.setMessage(LocaleController.getString("StopLoading",R.string.StopLoading));
  builder.setPositiveButton(LocaleController.getString("WaitMore",R.string.WaitMore),null);
  builder.setNegativeButton(LocaleController.getString("Stop",R.string.Stop),(dialogInterface,i) -> {
    if (onCancelListener != null) {
      onCancelListener.onCancel(AlertDialog.this);
    }
    dismiss();
  }
);
  builder.setOnDismissListener(dialog -> cancelDialog=null);
  cancelDialog=builder.show();
}
