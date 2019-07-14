/** 
 * Requests permission, overrides  {@param rationale}'s POSITIVE button dialog action.
 * @param permission The permission to ask for
 * @param code {@link #STORAGE_PERMISSION} or {@link #INSTALL_APK_PERMISSION}
 * @param rationale MaterialLayout to provide an additional rationale to the user if the permission was not grantedand the user would benefit from additional context for the use of the permission. For example, if the request has been denied previously.
 */
public void requestPermission(final String permission,final int code,@NonNull final MaterialDialog rationale,@NonNull final OnPermissionGranted onPermissionGranted){
  permissionCallbacks[code]=onPermissionGranted;
  if (ActivityCompat.shouldShowRequestPermissionRationale(this,permission)) {
    rationale.getActionButton(DialogAction.POSITIVE).setOnClickListener(v -> {
      ActivityCompat.requestPermissions(PermissionsActivity.this,new String[]{permission},code);
      rationale.dismiss();
    }
);
    rationale.show();
  }
 else {
    ActivityCompat.requestPermissions(this,new String[]{permission},code);
  }
}
