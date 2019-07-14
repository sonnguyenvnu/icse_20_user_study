@AfterPermissionGranted(REQUEST_CODE_DOWNLOAD_PERMISSION) private void download(){
  if (EffortlessPermissions.hasPermissions(this,PERMISSIONS_DOWNLOAD)) {
    downloadWithPermission();
  }
 else {
    EffortlessPermissions.requestPermissions(this,R.string.webview_download_permission_request_message,REQUEST_CODE_DOWNLOAD_PERMISSION,PERMISSIONS_DOWNLOAD);
  }
}
