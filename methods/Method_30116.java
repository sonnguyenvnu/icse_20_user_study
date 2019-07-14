@AfterPermissionDenied(REQUEST_CODE_SAVE_IMAGE_PERMISSION) private void onSaveImagePermissionDenied(){
  if (EffortlessPermissions.somePermissionPermanentlyDenied(this,PERMISSIONS_SAVE_IMAGE)) {
    OpenAppDetailsDialogFragment.show(R.string.gallery_save_permission_permanently_denied_message,R.string.open_settings,this);
  }
 else {
    ToastUtils.show(R.string.gallery_save_permission_denied,getActivity());
  }
}
