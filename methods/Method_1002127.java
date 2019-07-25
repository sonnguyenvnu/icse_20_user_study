private void unregister(Intent intent,String requestId){
  PendingIntent pendingIntent=intent.getParcelableExtra(EXTRA_APP);
  String packageName=PackageUtils.packageFromPendingIntent(pendingIntent);
  Log.d(TAG,"unregister[req]: " + intent.toString() + " extras=" + intent.getExtras());
  PushRegisterManager.completeRegisterRequest(this,database,new RegisterRequest().build(Utils.getBuild(this)).sender(intent.getStringExtra(EXTRA_SENDER)).checkin(LastCheckinInfo.read(this)).app(packageName),bundle -> {
    Intent outIntent=new Intent(ACTION_C2DM_REGISTRATION);
    outIntent.putExtras(bundle);
    Log.d(TAG,"unregister[res]: " + outIntent.toString() + " extras=" + outIntent.getExtras());
    sendReply(this,intent,packageName,outIntent);
  }
);
}
