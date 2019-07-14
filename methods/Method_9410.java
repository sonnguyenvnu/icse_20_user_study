@Override protected void onDialogDismiss(Dialog dialog){
  if (currentActivityType == TYPE_PHONE) {
    if (Build.VERSION.SDK_INT >= 23 && dialog == permissionsDialog && !permissionsItems.isEmpty()) {
      getParentActivity().requestPermissions(permissionsItems.toArray(new String[0]),6);
    }
  }
}
