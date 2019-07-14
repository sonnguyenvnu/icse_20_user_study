@Override protected void onDialogDismiss(Dialog dialog){
  if (Build.VERSION.SDK_INT >= 23) {
    if (dialog == permissionsDialog && !permissionsItems.isEmpty() && getParentActivity() != null) {
      try {
        getParentActivity().requestPermissions(permissionsItems.toArray(new String[0]),6);
      }
 catch (      Exception ignore) {
      }
    }
 else     if (dialog == permissionsShowDialog && !permissionsShowItems.isEmpty() && getParentActivity() != null) {
      try {
        getParentActivity().requestPermissions(permissionsShowItems.toArray(new String[0]),7);
      }
 catch (      Exception ignore) {
      }
    }
  }
}
