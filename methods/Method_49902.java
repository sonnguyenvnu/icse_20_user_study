public void downloadMultimediaMessage(final Context context,final String location,Uri uri,boolean byPush){
  if (location == null || mMap.get(location) != null) {
    return;
  }
  if (!isNotificationExist(context,location)) {
    return;
  }
  MmsDownloadReceiver receiver=new MmsDownloadReceiver();
  mMap.put(location,receiver);
  context.getApplicationContext().registerReceiver(receiver,new IntentFilter(receiver.mAction));
  Timber.v("receiving with system method");
  final String fileName="download." + String.valueOf(Math.abs(new Random().nextLong())) + ".dat";
  File mDownloadFile=new File(context.getCacheDir(),fileName);
  Uri contentUri=(new Uri.Builder()).authority(context.getPackageName() + ".MmsFileProvider").path(fileName).scheme(ContentResolver.SCHEME_CONTENT).build();
  Intent download=new Intent(receiver.mAction);
  download.putExtra(MmsReceivedReceiver.EXTRA_FILE_PATH,mDownloadFile.getPath());
  download.putExtra(MmsReceivedReceiver.EXTRA_LOCATION_URL,location);
  download.putExtra(MmsReceivedReceiver.EXTRA_TRIGGER_PUSH,byPush);
  download.putExtra(MmsReceivedReceiver.EXTRA_URI,uri);
  final PendingIntent pendingIntent=PendingIntent.getBroadcast(context,0,download,PendingIntent.FLAG_CANCEL_CURRENT);
  Bundle configOverrides=new Bundle();
  String httpParams=MmsConfig.getHttpParams();
  if (!TextUtils.isEmpty(httpParams)) {
    configOverrides.putString(SmsManager.MMS_CONFIG_HTTP_PARAMS,httpParams);
  }
  grantUriPermission(context,contentUri);
  SmsManager.getDefault().downloadMultimediaMessage(context,location,contentUri,configOverrides,pendingIntent);
}
