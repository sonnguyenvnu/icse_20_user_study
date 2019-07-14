@AfterPermissionGranted(REQUEST_CODE_SAVE_IMAGE_PERMISSION) private void saveImage(){
  if (EffortlessPermissions.hasPermissions(this,PERMISSIONS_SAVE_IMAGE)) {
    saveImageWithPermission();
  }
 else {
    EffortlessPermissions.requestPermissions(this,R.string.gallery_save_permission_request_message,REQUEST_CODE_SAVE_IMAGE_PERMISSION,PERMISSIONS_SAVE_IMAGE);
  }
}
