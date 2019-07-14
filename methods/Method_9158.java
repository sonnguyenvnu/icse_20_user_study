@TargetApi(Build.VERSION_CODES.M) public static void permissionDenied(final Activity activity,final Runnable onFinish){
  if (!activity.shouldShowRequestPermissionRationale(Manifest.permission.RECORD_AUDIO)) {
    AlertDialog dlg=new AlertDialog.Builder(activity).setTitle(LocaleController.getString("AppName",R.string.AppName)).setMessage(LocaleController.getString("VoipNeedMicPermission",R.string.VoipNeedMicPermission)).setPositiveButton(LocaleController.getString("OK",R.string.OK),null).setNegativeButton(LocaleController.getString("Settings",R.string.Settings),new DialogInterface.OnClickListener(){
      @Override public void onClick(      DialogInterface dialog,      int which){
        Intent intent=new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri=Uri.fromParts("package",activity.getPackageName(),null);
        intent.setData(uri);
        activity.startActivity(intent);
      }
    }
).show();
    dlg.setOnDismissListener(new DialogInterface.OnDismissListener(){
      @Override public void onDismiss(      DialogInterface dialog){
        if (onFinish != null)         onFinish.run();
      }
    }
);
  }
}
