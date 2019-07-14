public void onStart(){
  if (isInEditMode()) {
    return;
  }
  List<String> missingPermissions=getMissingPermissions();
  if (Build.VERSION.SDK_INT >= 23 && missingPermissions.size() > 0) {
    Activity activity=null;
    Context context=getContext();
    while (context instanceof ContextWrapper) {
      if (context instanceof Activity) {
        activity=(Activity)context;
      }
      context=((ContextWrapper)context).getBaseContext();
    }
    if (activity != null) {
      List<String> requestPermissions=new ArrayList<>();
      List<String> rationalePermissions=new ArrayList<>();
      for (      String permission : missingPermissions) {
        if (!activity.shouldShowRequestPermissionRationale(permission)) {
          requestPermissions.add(permission);
        }
 else {
          rationalePermissions.add(permission);
        }
      }
      if (requestPermissions.size() > 0) {
        activity.requestPermissions(requestPermissions.toArray(new String[requestPermissions.size()]),PERMISSION_REQUEST_CODE);
      }
      if (rationalePermissions.size() > 0 && mPermissionsListener != null) {
        mPermissionsListener.onPermissionsFailure();
      }
    }
    return;
  }
  if (mPermissionsListener != null) {
    mPermissionsListener.onPermissionsSuccess();
  }
  setFlash(mFlash);
  setImageMegaPixels(mImageMegaPixels);
  cameraFacing=getFacing() == CameraKit.FACING_BACK ? CameraFacing.BACK : CameraFacing.FRONT;
  mCameraPreview.start(cameraFacing);
}
