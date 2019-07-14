@AfterPermissionDenied(REQUEST_CODE_CAPTURE_IMAGE_PERMISSION) private void onCaptureImagePermissionDenied(){
  ToastUtils.show(R.string.broadcast_send_capture_image_permission_denied,getActivity());
  pickImage();
}
