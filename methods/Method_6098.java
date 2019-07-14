public static boolean isGoogleMapsInstalled(final BaseFragment fragment){
  try {
    ApplicationLoader.applicationContext.getPackageManager().getApplicationInfo("com.google.android.apps.maps",0);
    return true;
  }
 catch (  PackageManager.NameNotFoundException e) {
    if (fragment.getParentActivity() == null) {
      return false;
    }
    AlertDialog.Builder builder=new AlertDialog.Builder(fragment.getParentActivity());
    builder.setMessage(LocaleController.getString("InstallGoogleMaps",R.string.InstallGoogleMaps));
    builder.setPositiveButton(LocaleController.getString("OK",R.string.OK),(dialogInterface,i) -> {
      try {
        Intent intent=new Intent(Intent.ACTION_VIEW,Uri.parse("market://details?id=com.google.android.apps.maps"));
        fragment.getParentActivity().startActivityForResult(intent,500);
      }
 catch (      Exception e1) {
        FileLog.e(e1);
      }
    }
);
    builder.setNegativeButton(LocaleController.getString("Cancel",R.string.Cancel),null);
    fragment.showDialog(builder.create());
    return false;
  }
}
