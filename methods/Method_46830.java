@RequiresApi(api=Build.VERSION_CODES.M) public void requestInstallApkPermission(@NonNull final OnPermissionGranted onPermissionGranted){
  final MaterialDialog materialDialog=GeneralDialogCreation.showBasicDialog(this,R.string.grant_apkinstall_permission,R.string.grantper,R.string.grant,R.string.cancel);
  materialDialog.getActionButton(DialogAction.NEGATIVE).setOnClickListener(v -> materialDialog.dismiss());
  materialDialog.setCancelable(false);
  requestPermission(Manifest.permission.REQUEST_INSTALL_PACKAGES,INSTALL_APK_PERMISSION,materialDialog,onPermissionGranted);
}
