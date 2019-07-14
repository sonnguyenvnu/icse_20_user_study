public void maybeShowDismissalAlert(PhotoViewer photoViewer,Activity parentActivity,final Runnable okRunnable){
  if (editingText) {
    closeTextEnter(false);
    return;
  }
 else   if (pickingSticker) {
    closeStickersView();
    return;
  }
  if (hasChanges()) {
    if (parentActivity == null) {
      return;
    }
    AlertDialog.Builder builder=new AlertDialog.Builder(parentActivity);
    builder.setMessage(LocaleController.getString("DiscardChanges",R.string.DiscardChanges));
    builder.setTitle(LocaleController.getString("AppName",R.string.AppName));
    builder.setPositiveButton(LocaleController.getString("OK",R.string.OK),(dialogInterface,i) -> okRunnable.run());
    builder.setNegativeButton(LocaleController.getString("Cancel",R.string.Cancel),null);
    photoViewer.showAlertDialog(builder);
  }
 else {
    okRunnable.run();
  }
}
