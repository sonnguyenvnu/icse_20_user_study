@Override protected void onDialogDismiss(Dialog dialog){
  if (Build.VERSION.SDK_INT >= 23 && dialog == permissionsDialog && !permissionsItems.isEmpty()) {
    getParentActivity().requestPermissions(permissionsItems.toArray(new String[0]),6);
  }
  if (dialog == errorDialog) {
    finishFragment();
  }
}
