private void register(final Intent intent,String requestId){
  PendingIntent pendingIntent=intent.getParcelableExtra(EXTRA_APP);
  final String packageName=PackageUtils.packageFromPendingIntent(pendingIntent);
  GcmDatabase.App app=database.getApp(packageName);
  if (app == null && GcmPrefs.get(this).isConfirmNewApps()) {
    try {
      getPackageManager().getApplicationInfo(packageName,0);
      Intent i=new Intent(this,AskPushPermission.class);
      i.putExtra(EXTRA_PENDING_INTENT,intent);
      i.putExtra(EXTRA_APP,packageName);
      i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
      startActivity(i);
    }
 catch (    PackageManager.NameNotFoundException e) {
      replyNotAvailable(this,intent,packageName,requestId);
    }
  }
 else {
    registerAndReply(this,database,intent,packageName,requestId);
  }
}
