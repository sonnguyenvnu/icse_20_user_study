@AfterPermissionGranted(REQUEST_CODE_CAPTURE_IMAGE_PERMISSION) private void pickOrCaptureImage(){
  if (mImageUris.size() >= Broadcast.MAX_IMAGES_SIZE) {
    ToastUtils.show(R.string.broadcast_send_add_image_too_many,getActivity());
    return;
  }
  if (EffortlessPermissions.hasPermissions(this,PERMISSIONS_CAPTURE_IMAGE)) {
    pickOrCaptureImageWithPermission();
  }
 else   if (EffortlessPermissions.somePermissionPermanentlyDenied(this,PERMISSIONS_CAPTURE_IMAGE)) {
    ToastUtils.show(R.string.broadcast_send_capture_image_permission_permanently_denied_message,getActivity());
    pickImage();
  }
 else {
    EffortlessPermissions.requestPermissions(this,R.string.broadcast_send_capture_image_permission_request_message,REQUEST_CODE_CAPTURE_IMAGE_PERMISSION,PERMISSIONS_CAPTURE_IMAGE);
  }
}
