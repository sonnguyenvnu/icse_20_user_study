@AfterPermissionDenied(REQUEST_CODE_DOWNLOAD_PERMISSION) private void onDownloadPermissionDenied(){
  if (EffortlessPermissions.somePermissionPermanentlyDenied(this,PERMISSIONS_DOWNLOAD)) {
    OpenAppDetailsDialogFragment.show(R.string.webview_download_permission_permanently_denied_message,R.string.open_settings,this);
  }
 else {
    ToastUtils.show(R.string.webview_download_permission_denied,this);
  }
}
