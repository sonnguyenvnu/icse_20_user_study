public static void launchCloud(final HybridFileParcelable baseFile,final OpenMode serviceType,final Activity activity){
  final CloudStreamer streamer=CloudStreamer.getInstance();
  new Thread(() -> {
    try {
      streamer.setStreamSrc(baseFile.getInputStream(activity),baseFile.getName(),baseFile.length(activity));
      activity.runOnUiThread(() -> {
        try {
          File file=new File(Uri.parse(CloudUtil.stripPath(serviceType,baseFile.getPath())).getPath());
          Uri uri=Uri.parse(CloudStreamer.URL + Uri.fromFile(file).getEncodedPath());
          Intent i=new Intent(Intent.ACTION_VIEW);
          i.setDataAndType(uri,MimeTypes.getMimeType(baseFile.getPath(),baseFile.isDirectory()));
          PackageManager packageManager=activity.getPackageManager();
          List<ResolveInfo> resInfos=packageManager.queryIntentActivities(i,0);
          if (resInfos != null && resInfos.size() > 0)           activity.startActivity(i);
 else           Toast.makeText(activity,activity.getString(R.string.smb_launch_error),Toast.LENGTH_SHORT).show();
        }
 catch (        ActivityNotFoundException e) {
          e.printStackTrace();
        }
      }
);
    }
 catch (    Exception e) {
      e.printStackTrace();
    }
  }
).start();
}
