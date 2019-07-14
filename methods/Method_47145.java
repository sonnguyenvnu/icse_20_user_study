public static void launchSMB(final HybridFileParcelable baseFile,final Activity activity){
  final Streamer s=Streamer.getInstance();
  new Thread(){
    public void run(){
      try {
        s.setStreamSrc(new SmbFile(baseFile.getPath()),baseFile.getSize());
        activity.runOnUiThread(() -> {
          try {
            Uri uri=Uri.parse(Streamer.URL + Uri.fromFile(new File(Uri.parse(baseFile.getPath()).getPath())).getEncodedPath());
            Intent i=new Intent(Intent.ACTION_VIEW);
            i.setDataAndType(uri,MimeTypes.getMimeType(baseFile.getPath(),baseFile.isDirectory()));
            PackageManager packageManager=activity.getPackageManager();
            List<ResolveInfo> resInfos=packageManager.queryIntentActivities(i,0);
            if (resInfos != null && resInfos.size() > 0)             activity.startActivity(i);
 else             Toast.makeText(activity,activity.getResources().getString(R.string.smb_launch_error),Toast.LENGTH_SHORT).show();
          }
 catch (          ActivityNotFoundException e) {
            e.printStackTrace();
          }
        }
);
      }
 catch (      Exception e) {
        e.printStackTrace();
      }
    }
  }
.start();
}
