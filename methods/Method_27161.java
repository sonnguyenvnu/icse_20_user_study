public static boolean checkAndRequestReadWritePermission(@NonNull Activity activity){
  if (!isReadWritePermissionIsGranted(activity)) {
    requestReadWritePermission(activity);
    return false;
  }
 else   if (isExplanationNeeded(activity,Manifest.permission.READ_EXTERNAL_STORAGE) || isExplanationNeeded(activity,Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
    Toasty.error(App.getInstance(),activity.getString(R.string.read_write_permission_explanation),Toast.LENGTH_LONG).show();
    return false;
  }
  return true;
}
