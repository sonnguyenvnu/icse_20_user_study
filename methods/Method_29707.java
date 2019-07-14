private void requestPermissions(boolean requestCamera,boolean requestAudio){
  Activity activity=null;
  Context context=getContext();
  while (context instanceof ContextWrapper) {
    if (context instanceof Activity) {
      activity=(Activity)context;
    }
    context=((ContextWrapper)context).getBaseContext();
  }
  List<String> permissions=new ArrayList<>();
  if (requestCamera)   permissions.add(Manifest.permission.CAMERA);
  if (requestAudio)   permissions.add(Manifest.permission.RECORD_AUDIO);
  if (activity != null) {
    ActivityCompat.requestPermissions(activity,permissions.toArray(new String[permissions.size()]),CameraKit.Constants.PERMISSION_REQUEST_CAMERA);
  }
}
