private void showPermissionAlert(boolean byButton){
  if (getParentActivity() == null) {
    return;
  }
  AlertDialog.Builder builder=new AlertDialog.Builder(getParentActivity());
  builder.setTitle(LocaleController.getString("AppName",R.string.AppName));
  if (byButton) {
    builder.setMessage(LocaleController.getString("PermissionNoLocationPosition",R.string.PermissionNoLocationPosition));
  }
 else {
    builder.setMessage(LocaleController.getString("PermissionNoLocation",R.string.PermissionNoLocation));
  }
  builder.setNegativeButton(LocaleController.getString("PermissionOpenSettings",R.string.PermissionOpenSettings),(dialog,which) -> {
    if (getParentActivity() == null) {
      return;
    }
    try {
      Intent intent=new Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
      intent.setData(Uri.parse("package:" + ApplicationLoader.applicationContext.getPackageName()));
      getParentActivity().startActivity(intent);
    }
 catch (    Exception e) {
      FileLog.e(e);
    }
  }
);
  builder.setPositiveButton(LocaleController.getString("OK",R.string.OK),null);
  showDialog(builder.create());
}
