public static boolean checkApkInstallPermissions(final Context context){
  if (Build.VERSION.SDK_INT >= 26 && !ApplicationLoader.applicationContext.getPackageManager().canRequestPackageInstalls()) {
    AlertDialog.Builder builder=new AlertDialog.Builder(context);
    builder.setTitle(LocaleController.getString("AppName",R.string.AppName));
    builder.setMessage(LocaleController.getString("ApkRestricted",R.string.ApkRestricted));
    builder.setPositiveButton(LocaleController.getString("PermissionOpenSettings",R.string.PermissionOpenSettings),(dialogInterface,i) -> {
      try {
        context.startActivity(new Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES,Uri.parse("package:" + ApplicationLoader.applicationContext.getPackageName())));
      }
 catch (      Exception e) {
        FileLog.e(e);
      }
    }
);
    builder.setNegativeButton(LocaleController.getString("Cancel",R.string.Cancel),null);
    builder.show();
    return false;
  }
  return true;
}
